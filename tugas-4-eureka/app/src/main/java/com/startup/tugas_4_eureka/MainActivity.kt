package com.startup.tugas_4_eureka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.startup.tugas_4_eureka.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUser: GithubUsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterUser= GithubUsersAdapter()

        remoteGetUsers()

    }

    fun remoteGetUsers(){
        binding.rvProfil.apply {

            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=adapterUser
        }

        UsersApiClient.apiService.getUsers().enqueue(object : retrofit2.Callback<ArrayList<GithubUsers>>{
            override fun onResponse(
                call: Call<ArrayList<GithubUsers>>,
                response: Response<ArrayList<GithubUsers>>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null) {
                        if (data.isNotEmpty()){
                            setDataToAdapter(data)
                        }
                        Log.d("kosong","data kosong")

                    }
                    Log.e("ERROR",data.toString())
                }

            }

            override fun onFailure(call: Call<ArrayList<GithubUsers>>, t: Throwable) {
                Log.d("Debug",t.localizedMessage)
            }

        })
    }

    fun setDataToAdapter(data: ArrayList<GithubUsers>){
        adapterUser.setData(data)
    }
}