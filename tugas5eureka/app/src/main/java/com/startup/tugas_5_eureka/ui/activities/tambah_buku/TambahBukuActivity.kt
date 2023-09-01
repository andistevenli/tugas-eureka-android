package com.startup.tugas_5_eureka.ui.activities.tambah_buku

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.firebase.database.FirebaseDatabase
import com.kennyc.view.MultiStateView
import com.squareup.picasso.Picasso
import com.startup.tugas_5_eureka.R
import com.startup.tugas_5_eureka.databinding.ActivityTambahBukuBinding
import com.startup.tugas_5_eureka.repository.Repository
import com.startup.tugas_5_eureka.ui.activities.ViewModelFactory
import com.startup.tugas_5_eureka.ui.activities.detail_buku.DetailBukuViewModel

class TambahBukuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahBukuBinding
    private lateinit var repository: Repository
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: TambahBukuViewModel
    private var allowAddBook: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = Repository()
        viewModelFactory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[TambahBukuViewModel::class.java]

        //memakai package punya Anton Hadutski
        bindProgressButton(binding.btnTambahBuku)

        binding.btnTambahBuku.setOnClickListener(){
            setUpViewModel()

            binding.btnTambahBuku.attachTextChangeAnimator {

                fadeOutMills = 150 // current text fade out time in milliseconds. default 150
                fadeInMills = 150 // current text fade in time in milliseconds. default 150

                useCurrentTextColor = false // by default is true. handling text color based on the current color settings

                textColor = Color.WHITE // override button text color with single color
            }

            binding.btnTambahBuku.showProgress {
                buttonText = "Loading . . ."
                progressColor = Color.WHITE
            }

            binding.btnTambahBuku.hideProgress("Tambah Buku")

            finish()
        }
    }

    private fun setUpViewModel(){
        if (binding.etLinkFotoBuku.text.isEmpty()){
            binding.etLinkFotoBuku.error = "link foto buku wajib diisi"
            allowAddBook = false
        }
        if (binding.etJudulBuku.text.isEmpty()){
            binding.etJudulBuku.error = "judul buku wajib diisi"
            allowAddBook = false
        }
        if (binding.etPenerbitBuku.text.isEmpty()){
            binding.etPenerbitBuku.error = "nama penerbit buku wajib diisi"
            allowAddBook = false
        }
        if (binding.etTahunTerbit.text.isEmpty()){
            binding.etTahunTerbit.error = "tahun terbit buku wajib diisi"
            allowAddBook = false
        }
        if (binding.etTahunTerbit.text.length != 4){
            binding.etTahunTerbit.error = "tahun terbit tidak valid"
            allowAddBook = false
        }
        if (binding.etKategoriBuku.text.isEmpty()){
            binding.etKategoriBuku.error = "kategori buku wajib diisi"
            allowAddBook = false
        }

        if (allowAddBook){
            viewModel.addBook(binding.etLinkFotoBuku.text.toString(),binding.etJudulBuku.text.toString(),binding.etPenerbitBuku.text.toString(),binding.etTahunTerbit.text.toString(),binding.etKategoriBuku.text.toString())
        }
    }
}