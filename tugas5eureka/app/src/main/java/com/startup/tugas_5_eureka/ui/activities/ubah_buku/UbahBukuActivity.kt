package com.startup.tugas_5_eureka.ui.activities.ubah_buku

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.startup.tugas_5_eureka.databinding.ActivityUbahBukuBinding
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.utils.ImageLinkChecker
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class UbahBukuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUbahBukuBinding
    private val viewModel: UbahBukuViewModel by viewModels()
    private var allowEditBook: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        internetConnectionHandler()
    }

    /***
    *   this method is to add value to the edit text
     *   @author Andi
     *   @since September 15th, 2023
    * */
    private fun setUpEditText(){
        binding.etLinkFotoBuku.setText(intent.getStringExtra(EXTRA_LINK_COVER_BUKU))
        binding.etJudulBuku.setText(intent.getStringExtra(EXTRA_JUDUL_BUKU))
        binding.etPenerbitBuku.setText(intent.getStringExtra(EXTRA_PENERBIT_BUKU))
        binding.etTahunTerbit.setText(intent.getStringExtra(EXTRA_TAHUN_TERBIT_BUKU))
        binding.etKategoriBuku.setText(intent.getStringExtra(EXTRA_KATEGORI_BUKU))
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
            binding.etLinkFotoBuku.error = "link foto buku tidak valid"
            allowEditBook = false
        }
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
        if (tahunTerbit > currentYear){
            binding.etTahunTerbit.error = "maaf, kami belum melayani buku dari masa depan"
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

    companion object {
        const val EXTRA_ID_BUKU = "id_buku"
        const val EXTRA_LINK_COVER_BUKU = "link_cover_buku"
        const val EXTRA_JUDUL_BUKU = "judul_buku"
        const val EXTRA_PENERBIT_BUKU = "penerbit_buku"
        const val EXTRA_TAHUN_TERBIT_BUKU = "tahun_terbit_buku"
        const val EXTRA_KATEGORI_BUKU = "kategori_buku"
    }
}