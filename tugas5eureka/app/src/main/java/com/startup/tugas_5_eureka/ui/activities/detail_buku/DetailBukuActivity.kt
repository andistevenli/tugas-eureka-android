package com.startup.tugas_5_eureka.ui.activities.detail_buku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.kennyc.view.MultiStateView
import com.squareup.picasso.Picasso
import com.startup.tugas_5_eureka.R
import com.startup.tugas_5_eureka.databinding.ActivityDetailBukuBinding
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.ui.activities.hapus_buku.HapusBukuViewModel
import com.startup.tugas_5_eureka.ui.activities.ubah_buku.UbahBukuActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailBukuActivity : AppCompatActivity(), MultiStateView.StateListener {

    private lateinit var binding: ActivityDetailBukuBinding
    private  val detailBukuViewModel: DetailBukuViewModel by viewModels()
    private  val hapusBukuViewModel: HapusBukuViewModel by viewModels()
    private lateinit var multiStateView: MultiStateView
    private lateinit var linkCoverBuku : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        multiStateView = binding.stateDetailBuku
        multiStateView.listener = this

        setUpViewModel()

        binding.btnUbahBuku.setOnClickListener {
            val intentToUbahBuku= Intent(this, UbahBukuActivity::class.java)
            intentToUbahBuku.putExtra(UbahBukuActivity.EXTRA_ID_BUKU, intent.getStringExtra("id_buku"))
            intentToUbahBuku.putExtra(UbahBukuActivity.EXTRA_LINK_COVER_BUKU, linkCoverBuku)
            intentToUbahBuku.putExtra(UbahBukuActivity.EXTRA_JUDUL_BUKU, binding.tvJudulBuku.text)
            intentToUbahBuku.putExtra(UbahBukuActivity.EXTRA_PENERBIT_BUKU, binding.tvNamaPenerbit.text)
            intentToUbahBuku.putExtra(UbahBukuActivity.EXTRA_TAHUN_TERBIT_BUKU, binding.tvTahunTerbit.text)
            intentToUbahBuku.putExtra(UbahBukuActivity.EXTRA_KATEGORI_BUKU, binding.tvKategoriBuku.text)
            startActivity(intentToUbahBuku)
        }

        binding.btnHapusBuku.setOnClickListener {
            openDeleteDialog()
        }

        internetConnectionHandler()
    }

    /***
    *   this method is to open dialog for deletation confirm dialog
     *   @author Andi
     *   @since September 15th
    * */
    private fun openDeleteDialog(){
        val deleteDialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi Penghapusan Data")
            .setMessage("Apakah kamu yakin ingin menghapus data ini ? Data yang sudah dihapus bersifat permanen sehingga data tidak dapat dikembalikan")
            .setPositiveButton("Yakin", null)
            .setNegativeButton("Tidak", null)

        val myAlertDialog = deleteDialog.create()

        myAlertDialog.show()

        val myPositiveButton = myAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        myPositiveButton.setOnClickListener {
            hapusBukuViewModel.deleteBook(EXTRA_ID_BUKU)
            finish()
        }
    }

    /***
     *  this method is to implement view model and get the data and show them to the view
     *  @author Andi
     *  @since September 15th, 2023
    *
    * */
    private fun setUpViewModel() {
        detailBukuViewModel.getBuku(intent.getStringExtra("id_buku").toString()).observe(this) {result ->
            when(result){
                is Hasil.Loading -> multiStateView.viewState = MultiStateView.ViewState.LOADING
                is Hasil.Empty -> multiStateView.viewState = MultiStateView.ViewState.EMPTY
                is Hasil.Error -> {
                    multiStateView.viewState = MultiStateView.ViewState.ERROR
                    val error = binding.stateDetailBuku.getView(MultiStateView.ViewState.ERROR)
                    if (error != null){
                        val refresh = error.findViewById<Button>(R.id.btnRefreshDetailBuku)
                        refresh.setOnClickListener {
                            setUpViewModel()
                        }
                    }
                }
                is Hasil.Success -> {
                    multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    Picasso.get().load(result.data.linkCoverBuku).into(binding.ivDetailFotoBuku)
                    binding.tvJudulBuku.text = result.data.judulBuku
                    binding.tvNamaPenerbit.text = result.data.penerbitBuku
                    binding.tvTahunTerbit.text = result.data.tahunTerbitBuku
                    binding.tvKategoriBuku.text = result.data.kategoriBuku
                    linkCoverBuku = result.data.linkCoverBuku.toString()
                }
            }
        }
    }

    override fun onStateChanged(viewState: MultiStateView.ViewState) {}

    /***
     *   this method is to check user's internet connection
     *   @author Andi
     *   @since Septembet 15th, 2023
     * */
    private fun internetConnectionHandler(){
        hapusBukuViewModel.result.observe(this) {
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
    }
}