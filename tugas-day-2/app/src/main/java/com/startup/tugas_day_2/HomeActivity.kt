package com.startup.tugas_day_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var txtNama:TextView
    lateinit var txtEmail:TextView
    lateinit var txtJurusan:TextView
    lateinit var txtSemester:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        txtNama=findViewById(R.id.tvNama)
        txtEmail=findViewById(R.id.tvEmail)
        txtJurusan=findViewById(R.id.tvJurusan)
        txtSemester=findViewById(R.id.tvSemester)

        val intent = intent

        val nama=intent.getStringExtra("key_nama")
        val email=intent.getStringExtra("key_email")
        val jurusan=intent.getStringExtra("key_jurusan")
        val semester=intent.getStringExtra("key_semester")

        txtNama.text=nama
        txtEmail.text=email
        txtJurusan.text=jurusan
        txtSemester.text=semester
    }
}