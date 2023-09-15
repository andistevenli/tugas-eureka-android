package com.startup.tugas_5_eureka.ui.activities.daftar_buku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kennyc.view.MultiStateView
import com.startup.tugas_5_eureka.R
import com.startup.tugas_5_eureka.adapters.DaftarBukuAdapter
import com.startup.tugas_5_eureka.databinding.ActivityDaftarBukuBinding
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.model.BukuModel
import com.startup.tugas_5_eureka.ui.activities.tambah_buku.TambahBukuActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DaftarBukuActivity : AppCompatActivity(), MultiStateView.StateListener {

    private lateinit var binding: ActivityDaftarBukuBinding
    private  val viewModel: DaftarBukuViewModel by viewModels()
    private lateinit var multiStateView: MultiStateView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        multiStateView = binding.stateDaftarBuku
        multiStateView.listener = this

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

    /***
    *   this method is to implement view model, get the data and show them in the UI (view)
     *   @author Andi
     *   @since September 15th, 2023
    *
    * */
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
                        val refresh = error.findViewById<Button>(R.id.btnRefreshDaftarBuku)
                        refresh.setOnClickListener {
                            setUpViewModel()
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

    /***
     *   this method is to implement recycler view to the UI
     *   @author Andi
     *   @since September 15th, 2023
     *
     * */
    private fun setUpRecyclerView(list: List<BukuModel>){
        binding.rvDaftarBuku.apply {
            layoutManager = LinearLayoutManager(this@DaftarBukuActivity)
            adapter = DaftarBukuAdapter(list)
        }
    }

    override fun onStateChanged(viewState: MultiStateView.ViewState) {}
}