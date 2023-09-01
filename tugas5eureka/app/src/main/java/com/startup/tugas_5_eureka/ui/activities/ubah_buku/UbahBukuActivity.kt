package com.startup.tugas_5_eureka.ui.activities.ubah_buku

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.startup.tugas_5_eureka.R
import com.startup.tugas_5_eureka.databinding.ActivityUbahBukuBinding
import com.startup.tugas_5_eureka.repository.Repository
import com.startup.tugas_5_eureka.ui.activities.ViewModelFactory
import com.startup.tugas_5_eureka.ui.activities.tambah_buku.TambahBukuViewModel

class UbahBukuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUbahBukuBinding
    private lateinit var repository: Repository
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: UbahBukuViewModel
    private var allowEditBook: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = Repository()
        viewModelFactory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[UbahBukuViewModel::class.java]

        //memakai package punya Anton Hadutski
        bindProgressButton(binding.btnYakinUbahBuku)

        setUpEditText()

        binding.btnYakinUbahBuku.setOnClickListener(){
            setUpViewModel()

            binding.btnYakinUbahBuku.attachTextChangeAnimator {

                fadeOutMills = 150 // current text fade out time in milliseconds. default 150
                fadeInMills = 150 // current text fade in time in milliseconds. default 150

                useCurrentTextColor = false // by default is true. handling text color based on the current color settings

                textColor = Color.WHITE // override button text color with single color
            }

            binding.btnYakinUbahBuku.showProgress {
                buttonText = "Loading . . ."
                progressColor = Color.WHITE
            }

            binding.btnYakinUbahBuku.hideProgress("Ubah Buku")

            finish()
        }
    }

    private fun setUpEditText(){
        binding.etLinkFotoBuku.setText(intent.getStringExtra("link_cover_buku"))
        binding.etJudulBuku.setText(intent.getStringExtra("judul_buku"))
        binding.etPenerbitBuku.setText(intent.getStringExtra("penerbit_buku"))
        binding.etTahunTerbit.setText(intent.getStringExtra("tahun_terbit_buku"))
        binding.etKategoriBuku.setText(intent.getStringExtra("kategori_buku"))
    }

    private fun setUpViewModel(){
        if (binding.etLinkFotoBuku.text.isEmpty()){
            binding.etLinkFotoBuku.error = "link foto buku wajib diisi"
            allowEditBook = false
        }
        if (binding.etJudulBuku.text.isEmpty()){
            binding.etJudulBuku.error = "judul buku wajib diisi"
            allowEditBook = false
        }
        if (binding.etPenerbitBuku.text.isEmpty()){
            binding.etPenerbitBuku.error = "nama penerbit buku wajib diisi"
            allowEditBook = false
        }
        if (binding.etTahunTerbit.text.isEmpty()){
            binding.etTahunTerbit.error = "tahun terbit buku wajib diisi"
            allowEditBook = false
        }
        if (binding.etTahunTerbit.text.length != 4){
            binding.etTahunTerbit.error = "tahun terbit tidak valid"
            allowEditBook = false
        }
        if (binding.etKategoriBuku.text.isEmpty()){
            binding.etKategoriBuku.error = "kategori buku wajib diisi"
            allowEditBook = false
        }

        if (allowEditBook){
            viewModel.editBook(
                intent.getStringExtra("id_buku").toString(),
                binding.etLinkFotoBuku.text.toString(),
                binding.etJudulBuku.text.toString(),
                binding.etPenerbitBuku.text.toString(),
                binding.etTahunTerbit.text.toString(),
                binding.etKategoriBuku.text.toString())
        }
    }
}