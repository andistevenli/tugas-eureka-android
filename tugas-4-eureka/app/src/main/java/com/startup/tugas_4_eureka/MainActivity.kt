package com.startup.tugas_4_eureka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.startup.tugas_4_eureka.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GithubUsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= GithubUsersAdapter(arrayListOf())

        binding.rvProfil.adapter=adapter
        remoteGetUsers()

    }

    fun remoteGetUsers(){
        UsersApiClient.apiService.getUsers().enqueue(object : retrofit2.Callback<ArrayList<GithubUsers>>{
            override fun onResponse(
                call: Call<ArrayList<GithubUsers>>,
                response: Response<ArrayList<GithubUsers>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    setDataToAdapter(data!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<GithubUsers>>, t: Throwable) {
                Log.d("Error","Gagal mengampil data")
            }

        })
    }

    fun setDataToAdapter(data: ArrayList<GithubUsers>){
        adapter.setData(data)
    }
}