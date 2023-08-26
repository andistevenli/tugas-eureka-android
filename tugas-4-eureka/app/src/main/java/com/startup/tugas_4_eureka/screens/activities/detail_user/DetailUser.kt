package com.startup.tugas_4_eureka.screens.activities.detail_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kennyc.view.MultiStateView
import com.squareup.picasso.Picasso
import com.startup.tugas_4_eureka.R
import com.startup.tugas_4_eureka.adapters.ViewPagerAdapter
import com.startup.tugas_4_eureka.databinding.ActivityDetailUserBinding
import com.startup.tugas_4_eureka.repository.Repository
import com.startup.tugas_4_eureka.screens.ViewModelFactory
import com.startup.tugas_4_eureka.screens.fragments.follower.FollowerFragment
import com.startup.tugas_4_eureka.screens.fragments.following.FollowingFragment

class DetailUser : AppCompatActivity(), MultiStateView.StateListener {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailUserViewModel: DetailUserViewModel
    private lateinit var username: String
    private lateinit var multiStateView: MultiStateView
    private lateinit var followerFragment: FollowerFragment
    private lateinit var followingFragment: FollowingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        followingFragment = FollowingFragment()
        followerFragment = FollowerFragment()

        multiStateView = binding.stateDetailUser
        multiStateView.listener = this
        multiStateView.viewState = MultiStateView.ViewState.LOADING

        setupViewPager()

        val intent = intent
        username = intent.getStringExtra("username").toString()

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)

        detailUserViewModel = ViewModelProvider(this, viewModelFactory).get(DetailUserViewModel::class.java)

        getDetailUser()

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val bundle = Bundle()
        bundle.putString("username", username)
        followerFragment.arguments = bundle
        followingFragment.arguments = bundle
        if (followerFragment.isVisible){
            fragmentTransaction.add(R.id.flFollower, followerFragment).commit()
        } else {
            fragmentTransaction.add(R.id.flFollowing, followingFragment).commit()
        }


    }

    private fun getDetailUser(){
        detailUserViewModel.getGithubDetailUser(username)
        detailUserViewModel.liveData.observe(this, Observer{ response ->
            if (response.isSuccessful){
                if (response.body() == null){
                    multiStateView.viewState = MultiStateView.ViewState.EMPTY
                } else {
                    multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    Picasso.get().load(response.body()?.avatar_url).placeholder(R.drawable.ic_baseline_image_not_supported_24).into(binding.ivFotoProfilDetail)
                    binding.tvUsernameDetail.text = username
                    binding.tvNameDetail.text = response.body()?.name.toString()
                    binding.tvEmailDetail.text = response.body()?.email.toString()
                    binding.tvCompanyDetail.text = response.body()?.company.toString()
                }
            } else {
                multiStateView.viewState = MultiStateView.ViewState.ERROR
            }
        })
    }

    private fun setupViewPager(){
        val fragmentList = arrayListOf(FollowerFragment(),  FollowingFragment())
        val myViewPagerAdapter = ViewPagerAdapter(fragmentList, this?.supportFragmentManager!!, lifecycle)
        binding.vpFollowerFollowing.adapter = myViewPagerAdapter
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