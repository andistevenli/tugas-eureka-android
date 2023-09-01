package com.startup.tugas_5_eureka.ui.activities.detail_buku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kennyc.view.MultiStateView
import com.squareup.picasso.Picasso
import com.startup.tugas_5_eureka.R
import com.startup.tugas_5_eureka.adapters.DaftarBukuAdapter
import com.startup.tugas_5_eureka.databinding.ActivityDetailBukuBinding
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.repository.Repository
import com.startup.tugas_5_eureka.ui.activities.ViewModelFactory
import com.startup.tugas_5_eureka.ui.activities.daftar_buku.DaftarBukuActivity
import com.startup.tugas_5_eureka.ui.activities.daftar_buku.DaftarBukuViewModel
import com.startup.tugas_5_eureka.ui.activities.hapus_buku.HapusBukuViewModel
import com.startup.tugas_5_eureka.ui.activities.ubah_buku.UbahBukuActivity

class DetailBukuActivity : AppCompatActivity(), MultiStateView.StateListener {

    private lateinit var binding: ActivityDetailBukuBinding
    private lateinit var detailBukuViewModel: DetailBukuViewModel
    private lateinit var hapusBukuViewModel: HapusBukuViewModel
    private lateinit var multiStateView: MultiStateView
    private lateinit var repository: Repository
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var linkCoverBuku: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        multiStateView = binding.stateDetailBuku
        multiStateView.listener = this

        repository = Repository()
        viewModelFactory = ViewModelFactory(repository)

        detailBukuViewModel = ViewModelProvider(this, viewModelFactory)[DetailBukuViewModel::class.java]
        setUpViewModel()

        binding.btnUbahBuku.setOnClickListener {
            val intentToUbahBuku= Intent(this, UbahBukuActivity::class.java)
            intentToUbahBuku.putExtra("id_buku", intent.getStringExtra("id_buku"))
            intentToUbahBuku.putExtra("link_cover_buku", intent.getStringExtra("link_cover_buku"))
            intentToUbahBuku.putExtra("judul_buku", binding.tvJudulBuku.text)
            intentToUbahBuku.putExtra("penerbit_buku", binding.tvNamaPenerbit.text)
            intentToUbahBuku.putExtra("tahun_terbit_buku", binding.tvTahunTerbit.text)
            intentToUbahBuku.putExtra("kategori_buku", binding.tvKategoriBuku.text)
            startActivity(intentToUbahBuku)
        }

        binding.btnHapusBuku.setOnClickListener {
            openDeleteDialog()
        }
    }

    private fun openDeleteDialog(){
        val deleteDialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi Penghapusan Data")
            .setMessage("Apakah kamu yakin ingin menghapus data ini ? Data yang sudah dihapus bersifat permanen sehingga data tidak dapat dikembalikan")
            .setPositiveButton("Yakin", null)
            .setNegativeButton("Tidak", null)

        val myAlertDialog = deleteDialog.create()

        myAlertDialog.show()

        hapusBukuViewModel = ViewModelProvider(this, viewModelFactory)[HapusBukuViewModel::class.java]

        val myPositiveButton = myAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        myPositiveButton.setOnClickListener {
            hapusBukuViewModel.deleteBook(intent.getStringExtra("id_buku").toString())
            finish()
        }
    }

    private fun setUpViewModel() {
        detailBukuViewModel.getBuku(intent.getStringExtra("id_buku").toString()).observe(this) {result ->
            when(result){
                is Hasil.Loading -> multiStateView.viewState = MultiStateView.ViewState.LOADING
                is Hasil.Empty -> multiStateView.viewState = MultiStateView.ViewState.EMPTY
                is Hasil.Error -> multiStateView.viewState = MultiStateView.ViewState.ERROR
                is Hasil.Success -> {
                    multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    Picasso.get().load(intent.getStringExtra("link_cover_buku")).into(binding.ivDetailFotoBuku)
                    binding.tvJudulBuku.text = result.data.judulBuku
                    binding.tvNamaPenerbit.text = result.data.penerbitBuku
                    binding.tvTahunTerbit.text = result.data.tahunTerbitBuku
                    binding.tvKategoriBuku.text = result.data.kategoriBuku
                }
            }
        }
    }

    override fun onStateChanged(viewState: MultiStateView.ViewState) {}
}