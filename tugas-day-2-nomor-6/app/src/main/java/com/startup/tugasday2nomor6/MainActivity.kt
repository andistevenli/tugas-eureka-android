package com.startup.tugasday2nomor6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private  lateinit var dataList: ArrayList<Profil>
    lateinit var listFoto: Array<Int>
    lateinit var listNama: Array<String>
    lateinit var listEmail: Array<String>
    lateinit var listJurusan: Array<String>
    lateinit var listSemester: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        listFoto= arrayOf(
            R.drawable.ic_baseline_account_circle_24,
            R.drawable.ic_baseline_account_circle_24,
            R.drawable.ic_baseline_account_circle_24,
            R.drawable.ic_baseline_account_circle_24,
            R.drawable.ic_baseline_account_circle_24,)
        listNama= arrayOf("Andi","Budi","Rudi","Dadi","Redi")
        listEmail= arrayOf("andi@gmail.com","budi@gmail.com","rudi@gmail.com","redi@gmail.com")
        listJurusan= arrayOf("Sistem Informasi","Sistem Informasi","Teknologi Informasi","Teknik Informatika","Manajemen")
        listSemester= arrayOf("7","7","5","6","4")

        recyclerView=findViewById(R.id.recycleView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList= arrayListOf<Profil>()
        getData()

        recyclerView.setOnClickListener(){
            for (i in listFoto.indices){
                val dataClass =Profil(listFoto[i],listNama[i],listEmail[i],listJurusan[i],listSemester[i])

                val intent = Intent(applicationContext,DetailActivity::class.java)

                intent.putExtra("key_foto",dataClass.foto)
                intent.putExtra("key_nama",dataClass.nama)
                intent.putExtra("key_email",dataClass.email)
                intent.putExtra("key_jurusan",dataClass.jurusan)
                intent.putExtra("key_semester",dataClass.semester)

                startActivity(intent)
            }


        }
    }

    private  fun getData(){
        for (i in listFoto.indices){
            val dataClass =Profil(listFoto[i],listNama[i],listEmail[i],listJurusan[i],listSemester[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter=AdapterClass(dataList)
    }
}