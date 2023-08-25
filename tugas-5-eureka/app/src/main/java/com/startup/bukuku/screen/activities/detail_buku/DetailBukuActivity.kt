package com.startup.bukuku.screen.activities.detail_buku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import com.startup.bukuku.R
import com.startup.bukuku.databinding.ActivityDetailBukuBinding
import com.startup.bukuku.model.BukuModel
import com.startup.bukuku.screen.activities.daftar_buku.DaftarBukuActivity

class DetailBukuActivity : AppCompatActivity() {
    private lateinit var bindingDetailBuku: ActivityDetailBukuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetailBuku = ActivityDetailBukuBinding.inflate(layoutInflater)
        setContentView(bindingDetailBuku.root)

        bindingDetailBuku.btnUbahBuku.setOnClickListener(){
            openUpdateDialog(intent.getStringExtra("idBuku").toString())
        }

        bindingDetailBuku.btnHapusBuku.setOnClickListener(){
            openDeleteDialog(intent.getStringExtra("idBuku").toString())
        }

        setValuesToViews()
    }

    private fun openDeleteDialog(idBuku: String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Books")

        val myDialog = AlertDialog.Builder(this)
            .setTitle("Yakin Nih ??")
            .setMessage("Kamu yakin mau hapus ini ? Kayak mantan, datanya ga bisa balik lagi loh.")
            .setPositiveButton("Yakin Dong", null)
            .setNegativeButton("Ga Dulu Deh", null)

        val myAlertDialog = myDialog.create()

        myAlertDialog.show()

        val myPositiveButton = myAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        myPositiveButton.setOnClickListener {
            dbRef.child(idBuku).removeValue().addOnSuccessListener {
//                myAlertDialog.cancel()
                Toast.makeText(applicationContext, "Data Buku Berhasil Dihapus",Toast.LENGTH_SHORT).show()
                val intentToDaftarBuku = Intent(this, DaftarBukuActivity::class.java)
                finish()
                startActivity(intentToDaftarBuku)
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Data Buku Gagal Diubah",Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun openUpdateDialog(idBuku: String){
        val myDialog = AlertDialog.Builder(this)
        val myDialogView = layoutInflater.inflate(R.layout.fragment_edit_buku,null)

        myDialog.setView(myDialogView)

        val etLinkFotoBuku = myDialogView.findViewById<EditText>(R.id.etLinkFotoBuku)
        val etJudulBuku = myDialogView.findViewById<EditText>(R.id.etJudulBuku)
        val etPenerbitBuku = myDialogView.findViewById<EditText>(R.id.etPenerbitBuku)
        val etTahunTerbitBuku = myDialogView.findViewById<EditText>(R.id.etTahunTerbit)
        val etKategoriBuku = myDialogView.findViewById<EditText>(R.id.etKategoriBuku)
        val btnUbahBuku = myDialogView.findViewById<Button>(R.id.btnYakinUbahBuku)

        etLinkFotoBuku.setText(intent.getStringExtra("linkFotoBuku").toString())
        etJudulBuku.setText(intent.getStringExtra("judulBuku").toString())
        etPenerbitBuku.setText(intent.getStringExtra("penerbitBuku").toString())
        etTahunTerbitBuku.setText(intent.getStringExtra("tahunTerbitBuku").toString())
        etKategoriBuku.setText(intent.getStringExtra("kategoriBuku").toString())

        myDialog.setTitle("Ubah Data Buku")

        myDialog.create().show()

        btnUbahBuku.setOnClickListener(){
            updateDataBuku(idBuku, etLinkFotoBuku.text.toString(), etJudulBuku.text.toString(), etPenerbitBuku.text.toString(), etTahunTerbitBuku.text.toString(), etKategoriBuku.text.toString())

            //update data yg ada pada dialog ke detail buku activity
            Picasso.get().load(etLinkFotoBuku.text.toString()).placeholder(R.drawable.ic_baseline_image_24).into(bindingDetailBuku.ivDetailFotoBuku)
            bindingDetailBuku.tvJudulBuku.text = etJudulBuku.text
            bindingDetailBuku.tvNamaPenerbit.text = etPenerbitBuku.text
            bindingDetailBuku.tvTahunTerbit.text = etTahunTerbitBuku.text
            bindingDetailBuku.tvKategoriBuku.text = etKategoriBuku.text

            myDialog.create().cancel()
        }


    }

    private fun updateDataBuku(idBuku: String, linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String){
        val dbRef=FirebaseDatabase.getInstance().getReference("Books")
        val dataBuku = BukuModel(idBuku,linkFotoBuku,judulBuku,penerbitBuku,tahunTerbitBuku,kategoriBuku)

        dbRef.child(idBuku).setValue(dataBuku)
            .addOnCompleteListener {
                Toast.makeText(applicationContext, "Data Buku Berhasil Diubah",Toast.LENGTH_SHORT).show()
            }.addOnCanceledListener {
                Toast.makeText(applicationContext,"Data Buku Gagal Diubah",Toast.LENGTH_SHORT).show()
                Log.e("ERROR","gagal")
            }
    }

    private fun setValuesToViews(){
        Picasso.get().load(intent.getStringExtra("linkFotoBuku")).placeholder(R.drawable.ic_baseline_image_24).into(bindingDetailBuku.ivDetailFotoBuku)
        bindingDetailBuku.tvJudulBuku.text = intent.getStringExtra("judulBuku")
        bindingDetailBuku.tvNamaPenerbit.text = intent.getStringExtra("penerbitBuku")
        bindingDetailBuku.tvTahunTerbit.text = intent.getStringExtra("tahunTerbitBuku")
        bindingDetailBuku.tvKategoriBuku.text = intent.getStringExtra("kategoriBuku")
    }
}