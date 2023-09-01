package com.startup.tugas_4_eureka.screens.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.kennyc.view.MultiStateView
import com.startup.tugas_4_eureka.adapters.GithubUsersAdapter
import com.startup.tugas_4_eureka.databinding.ActivityMainBinding
import com.startup.tugas_4_eureka.repository.Hasil
import com.startup.tugas_4_eureka.repository.Repository
import com.startup.tugas_4_eureka.screens.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MultiStateView.StateListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUser: GithubUsersAdapter
//    private lateinit var mainViewModel: MainViewModel
    private lateinit var multiStateView: MultiStateView

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        multiStateView = binding.stateMain
        multiStateView.listener = this

        adapterUser = GithubUsersAdapter()
        setUpRecyclerView()

//        val repository = Repository()
//        val viewModelFactory = ViewModelFactory(repository)

//        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.getGithubUsers().observe(this) {
            when(it){
                is Hasil.Empty -> multiStateView.viewState = MultiStateView.ViewState.EMPTY
                is Hasil.Loading -> multiStateView.viewState = MultiStateView.ViewState.LOADING
                is Hasil.Error -> multiStateView.viewState = MultiStateView.ViewState.ERROR
                is Hasil.Success -> {
                    multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    adapterUser.setData(it.data)
                }
            }
        }

        binding.btnCrash.setOnClickListener{
            FirebaseCrashlytics.getInstance().log("ANDI CRASHLYTICS")
            throw RuntimeException()
        }
    }

    private fun setUpRecyclerView(){
        binding.rvProfil.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onStateChanged(viewState: MultiStateView.ViewState) {}
}