package com.startup.tugas_day_2_nomor_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter
    }

    private  fun  init(){
        var data = ArrayList<Profil>();
        data.add(Profil(R.drawable.ice_cream,"Rudi","rudi@gmail.com","Sistem Informasi","7"))
        data.add(Profil(R.drawable.ice_cream,"Andi","andi@gmail.com","Sistem Informasi","5"))
        data.add(Profil(R.drawable.ice_cream,"Budi","budi@gmail.com","Teknologi Informasi","6"))
        data.add(Profil(R.drawable.ice_cream,"Hendi","hendi@gmail.com","Teknik Informatika","3"))
        data.add(Profil(R.drawable.ice_cream,"Alvin","alvin@gmail.com","Manajemen","4"))

        adapter=MyAdapter(data)
    }
}