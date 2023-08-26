package com.startup.tugas_4_eureka.screens.activities.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.startup.tugas_4_eureka.adapters.GithubUsersAdapter
import com.startup.tugas_4_eureka.databinding.ActivityMainBinding
import com.startup.tugas_4_eureka.repository.Repository
import com.startup.tugas_4_eureka.screens.ViewModelFactory

class MainActivity : AppCompatActivity(), MultiStateView.StateListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUser: GithubUsersAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var multiStateView: MultiStateView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        multiStateView = binding.stateMain
        multiStateView.listener = this
        multiStateView.viewState = MultiStateView.ViewState.LOADING

        adapterUser = GithubUsersAdapter()
        setUpRecyclerView()

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)

        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        mainViewModel.getGithubUsers()
        mainViewModel.liveData.observe(this, Observer{ response ->
            if (response.isSuccessful){
                if (response.body() == null){
                    multiStateView.viewState = MultiStateView.ViewState.EMPTY
                } else {
                    multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    response.body()?.let { adapterUser.setData(it) }
                }
            } else {
                multiStateView.viewState = MultiStateView.ViewState.ERROR
            }
        })
    }

    private fun setUpRecyclerView(){
        binding.rvProfil.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onStateChanged(viewState: MultiStateView.ViewState) {
        when(viewState){
            MultiStateView.ViewState.LOADING -> Log.d("LOADING","state loading")
            MultiStateView.ViewState.EMPTY -> Log.d("EMPTY","state empty")
            MultiStateView.ViewState.ERROR -> Log.d("ERROR","state error")
            else -> Log.d("CONTENT","state content")
        }
    }
}