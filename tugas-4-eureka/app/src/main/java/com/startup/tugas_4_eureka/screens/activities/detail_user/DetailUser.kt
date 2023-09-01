package com.startup.tugas_4_eureka.screens.activities.detail_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.kennyc.view.MultiStateView
import com.squareup.picasso.Picasso
import com.startup.tugas_4_eureka.R
import com.startup.tugas_4_eureka.adapters.ViewPagerAdapter
import com.startup.tugas_4_eureka.databinding.ActivityDetailUserBinding
import com.startup.tugas_4_eureka.repository.Hasil
import com.startup.tugas_4_eureka.repository.Repository
import com.startup.tugas_4_eureka.screens.ViewModelFactory
import com.startup.tugas_4_eureka.screens.fragments.follower.FollowerFragment
import com.startup.tugas_4_eureka.screens.fragments.following.FollowingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailUser : AppCompatActivity(), MultiStateView.StateListener {
    private lateinit var binding: ActivityDetailUserBinding
//    private lateinit var detailUserViewModel: DetailUserViewModel
    private lateinit var username: String
    private lateinit var linkFotoProfil: String
    private lateinit var multiStateView: MultiStateView
    private lateinit var followerFragment: FollowerFragment
    private lateinit var followingFragment: FollowingFragment
    private val detailUserViewModel : DetailUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        followingFragment = FollowingFragment()
        followerFragment = FollowerFragment()

        multiStateView = binding.stateDetailUser
        multiStateView.listener = this


        val intent = intent
        username = intent.getStringExtra("username").toString()
        linkFotoProfil = intent.getStringExtra("link_foto_profil").toString()

//        val repository = Repository()
//        val viewModelFactory = ViewModelFactory(repository)

//        detailUserViewModel = ViewModelProvider(this, viewModelFactory).get(DetailUserViewModel::class.java)

        getDetailUser()

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.username = intent.getStringExtra("username").toString()
        binding.vpFollowerFollowing.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tlFollowingFollower, binding.vpFollowerFollowing) {tab,position ->
            val tabStringList = listOf<String>("Follower","Following")
            tab.text = tabStringList[position]
        }.attach()

    }

    private fun getDetailUser(){
        detailUserViewModel.getGithubDetailUser(intent.getStringExtra("username").toString()).observe(this){
            when(it){
                is Hasil.Loading -> multiStateView.viewState = MultiStateView.ViewState.LOADING
                is Hasil.Empty -> multiStateView.viewState = MultiStateView.ViewState.EMPTY
                is Hasil.Error -> multiStateView.viewState = MultiStateView.ViewState.ERROR
                is Hasil.Success -> {
                    multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    Picasso.get().load(linkFotoProfil).into(binding.ivFotoProfilDetail)
                    binding.tvUsernameDetail.text = username
                    binding.tvNameDetail.text = it.data.name
                    binding.tvEmailDetail.text = it.data.email
                    binding.tvCompanyDetail.text = it.data.company
                }
            }
        }
    }

    override fun onStateChanged(viewState: MultiStateView.ViewState) {}
}