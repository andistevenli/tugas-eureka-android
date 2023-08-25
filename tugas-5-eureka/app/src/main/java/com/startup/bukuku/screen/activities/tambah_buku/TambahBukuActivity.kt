package com.startup.bukuku.screen.activities.tambah_buku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.startup.bukuku.databinding.ActivityTambahBukuBinding
import com.startup.bukuku.model.BukuModel

class TambahBukuActivity : AppCompatActivity() {
    private lateinit var bindingTambahBuku: ActivityTambahBukuBinding
    private lateinit var dbRef: DatabaseReference
    private var allowAddBook: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingTambahBuku = ActivityTambahBukuBinding.inflate(layoutInflater)
        setContentView(bindingTambahBuku.root)

        //inisialisasi koneksi database
        dbRef = FirebaseDatabase.getInstance().getReference("Books")

        bindingTambahBuku.btnTambahBuku.setOnClickListener(){
            addBookData()
            Log.d("DEBUG","berhasil")
        }
    }

    private fun addBookData(){
        //get value
        val linkFotoBuku: String = bindingTambahBuku.etLinkFotoBuku.text.toString()
        val judulBuku : String= bindingTambahBuku.etJudulBuku.text.toString()
        val namaPenerbitBuku : String = bindingTambahBuku.etPenerbitBuku.text.toString()
        val tahunTerbitBuku : String = bindingTambahBuku.etTahunTerbit.text.toString()
        val kategoriBuku : String = bindingTambahBuku.etKategoriBuku.text.toString()

        if (linkFotoBuku.isEmpty()){
            bindingTambahBuku.etLinkFotoBuku.error = "link foto buku wajib diisi"
            allowAddBook = false
        }
        if (judulBuku.isEmpty()){
            bindingTambahBuku.etJudulBuku.error = "judul buku wajib diisi"
            allowAddBook = false
        }
        if (namaPenerbitBuku.isEmpty()){
            bindingTambahBuku.etPenerbitBuku.error = "nama penerbit buku wajib diisi"
            allowAddBook = false
        }
        if (tahunTerbitBuku.isEmpty()){
            bindingTambahBuku.etTahunTerbit.error = "tahun terbit buku wajib diisi"
            allowAddBook = false
        }
        if (tahunTerbitBuku.length!=4){
            bindingTambahBuku.etTahunTerbit.error = "tahun terbit tidak valid"
            allowAddBook = false
        }
        if (tahunTerbitBuku.substring(0,1) == "0"){
            bindingTambahBuku.etTahunTerbit.error = "tahun terbit tidak valid"
            allowAddBook = false
        }
        if (kategoriBuku.isEmpty()){
            bindingTambahBuku.etKategoriBuku.error = "kategori buku wajib diisi"
            allowAddBook = false
        }

        if (allowAddBook){
            //id harus unik
            val idBuku = dbRef.push().key!!

            val buku = BukuModel(idBuku,linkFotoBuku,judulBuku,namaPenerbitBuku,tahunTerbitBuku,kategoriBuku)

            dbRef.child(idBuku).setValue(buku)
                .addOnCompleteListener {
                    Toast.makeText(this,"Data Buku Berhasil Ditambahkan",Toast.LENGTH_SHORT).show()
                    bindingTambahBuku.etLinkFotoBuku.text.clear()
                    bindingTambahBuku.etJudulBuku.text.clear()
                    bindingTambahBuku.etPenerbitBuku.text.clear()
                    bindingTambahBuku.etTahunTerbit.text.clear()
                    bindingTambahBuku.etKategoriBuku.text.clear()
                }.addOnCanceledListener {
                    Toast.makeText(this,"Data Buku Gagal Ditambahkan",Toast.LENGTH_SHORT).show()
                    Log.e("ERROR","gagal")
                }
        }


    }
}