package com.startup.tugas_5_eureka.ui.activities.tambah_buku

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.startup.tugas_5_eureka.databinding.ActivityTambahBukuBinding
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.utils.ImageLinkChecker
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class TambahBukuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTambahBukuBinding
    private val viewModel: TambahBukuViewModel by viewModels()
    private var allowAddBook: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        internetConnectionHandler()
    }

    /***
    *   this method is to impelment the view model
     *   @author Andi
     *   @since September 15th, 2023
    * */
    private fun setUpViewModel(){
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val tahunTerbit : Int = binding.etTahunTerbit.text.toString().toInt()
        if (!ImageLinkChecker.checkImageLinkExtention(binding.etLinkFotoBuku.text.toString())){
            binding.etLinkFotoBuku.error = "Link foto buku tidak valid"
            allowAddBook = false
        }
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
        if (tahunTerbit > currentYear){
            binding.etTahunTerbit.error = "tahun memang cepat berlalu, tapi ga melebihi tahun ini juga yah"
        }
        if (binding.etKategoriBuku.text.isEmpty()){
            binding.etKategoriBuku.error = "kategori buku wajib diisi"
            allowAddBook = false
        }
        if (allowAddBook){
            viewModel.addBook(binding.etLinkFotoBuku.text.toString(),binding.etJudulBuku.text.toString(),binding.etPenerbitBuku.text.toString(),binding.etTahunTerbit.text.toString(),binding.etKategoriBuku.text.toString())
        }
    }

    /***
    *   this method is to check user's internet connection
     *   @author Andi
     *   @since Septembet 15th, 2023
    * */
    private fun internetConnectionHandler(){
        viewModel.result.observe(this) {
            when(it){
                is Hasil.Error -> Toast.makeText(
                    this,
                    it.error,
                    Toast.LENGTH_SHORT
                ).show()
                else -> {}
            }
        }
    }
}