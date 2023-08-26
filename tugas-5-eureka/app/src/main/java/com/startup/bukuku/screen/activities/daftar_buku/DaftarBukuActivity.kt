
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
import com.kennyc.view.MultiStateView
import com.startup.bukuku.adapters.DaftarBukuAdapter
import com.startup.bukuku.databinding.ActivityDaftarBukuBinding
import com.startup.bukuku.model.BukuModel
import com.startup.bukuku.screen.activities.detail_buku.DetailBukuActivity
import com.startup.bukuku.screen.activities.tambah_buku.TambahBukuActivity

class DaftarBukuActivity : AppCompatActivity() {
    private lateinit var bindingDaftarBuku: ActivityDaftarBukuBinding
    private lateinit var daftarBukuViewModel : DaftarBukuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDaftarBuku = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(bindingDaftarBuku.root)

        daftarBukuViewModel = ViewModelProvider(this).get(DaftarBukuViewModel::class.java)
        daftarBukuViewModel.getDataBuku(this@DaftarBukuActivity)

        bindingDaftarBuku.btnTambahBuku.setOnClickListener(){
            val intentToTambahBuku = Intent(this, TambahBukuActivity::class.java)
            startActivity(intentToTambahBuku)
        }

    }

    override fun onResume() {
        super.onResume()
        daftarBukuViewModel.getDataBuku(this)
    }
}