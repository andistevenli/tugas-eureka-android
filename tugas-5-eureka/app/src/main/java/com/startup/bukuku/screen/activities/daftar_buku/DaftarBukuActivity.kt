
package com.startup.bukuku.screen.activities.daftar_buku

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.startup.bukuku.adapters.DaftarBukuAdapter
import com.startup.bukuku.databinding.ActivityDaftarBukuBinding
import com.startup.bukuku.model.BukuModel
import com.startup.bukuku.screen.activities.detail_buku.DetailBukuActivity
import com.startup.bukuku.screen.activities.tambah_buku.TambahBukuActivity

class DaftarBukuActivity : AppCompatActivity() {
    private lateinit var bindingDaftarBuku: ActivityDaftarBukuBinding
    private lateinit var listBuku: ArrayList<BukuModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var daftarBukuAdapter: DaftarBukuAdapter
//    private lateinit var viewModel: DaftarBukuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDaftarBuku = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(bindingDaftarBuku.root)

        bindingDaftarBuku.rvDaftarBuku.layoutManager = LinearLayoutManager(this)
        bindingDaftarBuku.rvDaftarBuku.setHasFixedSize(true)
        listBuku = arrayListOf<BukuModel>()

        getDataBuku()
//        viewModel = ViewModelProvider(this).get(DaftarBukuViewModel::class.java)
//        viewModel.getDataBuku(this)

        bindingDaftarBuku.btnTambahBuku.setOnClickListener(){
            val intentToTambahBuku = Intent(this, TambahBukuActivity::class.java)
            startActivity(intentToTambahBuku)
        }

    }

    private fun getDataBuku(){
        dbRef = FirebaseDatabase.getInstance().getReference("Books")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listBuku.clear()
                if (snapshot.exists()){
                    for (bukuSnap in snapshot.children){
                        val dataBuku = bukuSnap.getValue(BukuModel::class.java)
                        Log.d("BUKU",dataBuku.toString())
                        listBuku.add(dataBuku!!)
                    }

                }
                Log.d("ASDASD",listBuku.toString())
                daftarBukuAdapter = DaftarBukuAdapter(listBuku)
                bindingDaftarBuku.rvDaftarBuku.adapter = daftarBukuAdapter
                daftarBukuAdapter.setOnItemClickListener(object : DaftarBukuAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intentToDetailBuku = Intent(this@DaftarBukuActivity,
                            DetailBukuActivity::class.java)
                        Log.d("ANDI1",listBuku[position].linkFotoBuku.toString())
                        intentToDetailBuku.putExtra("idBuku",listBuku[position].idBuku)
                        intentToDetailBuku.putExtra("linkFotoBuku",listBuku[position].linkFotoBuku)
                        intentToDetailBuku.putExtra("judulBuku",listBuku[position].judulBuku)
                        intentToDetailBuku.putExtra("penerbitBuku",listBuku[position].penerbitBuku)
                        intentToDetailBuku.putExtra("tahunTerbitBuku",listBuku[position].tahunTerbitBuku)
                        intentToDetailBuku.putExtra("kategoriBuku",listBuku[position].kategoriBuku)
                        startActivity(intentToDetailBuku)
                    }
                })
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("ERROR","gagal mengambil data")
            }
        })
    }
}