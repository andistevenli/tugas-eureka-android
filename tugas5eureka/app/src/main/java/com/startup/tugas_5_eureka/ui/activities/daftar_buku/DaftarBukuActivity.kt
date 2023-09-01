package com.startup.tugas_5_eureka.ui.activities.daftar_buku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.core.Repo
import com.kennyc.view.MultiStateView
import com.startup.tugas_5_eureka.R
import com.startup.tugas_5_eureka.adapters.DaftarBukuAdapter
import com.startup.tugas_5_eureka.databinding.ActivityDaftarBukuBinding
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.model.BukuModel
import com.startup.tugas_5_eureka.repository.Repository
import com.startup.tugas_5_eureka.ui.activities.ViewModelFactory
import com.startup.tugas_5_eureka.ui.activities.tambah_buku.TambahBukuActivity

class DaftarBukuActivity : AppCompatActivity(), MultiStateView.StateListener {

    private lateinit var binding: ActivityDaftarBukuBinding
    private lateinit var viewModel: DaftarBukuViewModel
    private lateinit var multiStateView: MultiStateView
    private lateinit var repository: Repository
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var listBuku: List<BukuModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        multiStateView = binding.stateDaftarBuku
        multiStateView.listener = this

        repository = Repository()
        viewModelFactory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[DaftarBukuViewModel::class.java]
        setUpViewModel()

        binding.btnTambahBukuLayoutDaftar.setOnClickListener {
            val intentToTambahBuku = Intent(this, TambahBukuActivity::class.java)
            startActivity(intentToTambahBuku)
        }

//        binding.btnCrash.setOnClickListener {
//            FirebaseCrashlytics.getInstance().log("ANDI CRASH")
//            throw  RuntimeException()
//        }

    }

    private fun setUpViewModel(){
        viewModel.listBuku.observe(this) {result ->
            when(result){
                is Hasil.Loading -> multiStateView.viewState = MultiStateView.ViewState.LOADING
                is Hasil.Empty -> {
                    multiStateView.viewState = MultiStateView.ViewState.EMPTY
                    val empty = binding.stateDaftarBuku.getView(MultiStateView.ViewState.EMPTY)
                    if (empty != null){
                        val btnTambahBukuLayoutEmpty : FloatingActionButton = empty.findViewById(R.id.btnTambahBukuLayoutEmpty)
                        btnTambahBukuLayoutEmpty .setOnClickListener {
                            val intentToTambahBuku = Intent(this, TambahBukuActivity::class.java)
                            startActivity(intentToTambahBuku)
                        }
                    }
                }
                is Hasil.Error -> {
                    multiStateView.viewState = MultiStateView.ViewState.ERROR
                    val error = binding.stateDaftarBuku.getView(MultiStateView.ViewState.ERROR)
                    if (error != null){
                        val btnTambahBukuLayoutError = error.findViewById<FloatingActionButton>(R.id.btnTambahBukuLayoutError)
                        btnTambahBukuLayoutError.setOnClickListener {
                            val intentToTambahBuku = Intent(this, TambahBukuActivity::class.java)
                            startActivity(intentToTambahBuku)
                        }
                    }
                }
                is Hasil.Success -> {
                    multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    setUpRecyclerView(result.data)
                }
            }
        }
    }

    private fun setUpRecyclerView(list: List<BukuModel>){
        binding.rvDaftarBuku.apply {
            layoutManager = LinearLayoutManager(this@DaftarBukuActivity)
            adapter = DaftarBukuAdapter(list)
        }
    }

    override fun onStateChanged(viewState: MultiStateView.ViewState) {}
}