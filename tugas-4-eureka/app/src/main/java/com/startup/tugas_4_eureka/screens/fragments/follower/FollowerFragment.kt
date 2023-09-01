package com.startup.tugas_4_eureka.screens.fragments.follower

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.startup.tugas_4_eureka.R
import com.startup.tugas_4_eureka.adapters.GithubUsersAdapter
import com.startup.tugas_4_eureka.databinding.FragmentFollowerBinding
import com.startup.tugas_4_eureka.repository.Hasil
import com.startup.tugas_4_eureka.repository.Repository
import com.startup.tugas_4_eureka.screens.ViewModelFactory
import com.startup.tugas_4_eureka.screens.activities.detail_user.DetailUser
import com.startup.tugas_4_eureka.screens.activities.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowerFragment : Fragment(), MultiStateView.StateListener {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterUser: GithubUsersAdapter
//    private lateinit var followerViewModel: FollowerViewModel
    private lateinit var multiStateView: MultiStateView
    private val followerViewModel : FollowerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val username = bundle!!.getString("username").toString()

        multiStateView = binding.stateFollower
        multiStateView.listener = this

        adapterUser = GithubUsersAdapter()
        setUpRecyclerView()

//        val repository = Repository()
//        val viewModelFactory = ViewModelFactory(repository)
//
//        followerViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[FollowerViewModel::class.java]
        followerViewModel.getGithubDetailUserFollower(username).observe(requireActivity()) {
            when(it){
                is Hasil.Empty -> multiStateView.viewState = MultiStateView.ViewState.EMPTY
                is Hasil.Error -> multiStateView.viewState = MultiStateView.ViewState.ERROR
                is Hasil.Loading -> multiStateView.viewState = MultiStateView.ViewState.LOADING
                is Hasil.Success -> {
                    multiStateView.viewState = MultiStateView.ViewState.CONTENT
                    adapterUser.setData(it.data)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpRecyclerView(){
        binding.rvProfil.apply {
            adapter = adapterUser
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onStateChanged(viewState: MultiStateView.ViewState) {}
}