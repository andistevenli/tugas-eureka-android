package com.startup.tugasday2nomor6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    lateinit var txtNama: TextView
    lateinit var txtEmail: TextView
    lateinit var txtJurusan: TextView
    lateinit var txtSemester: TextView
    lateinit var ivFoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ivFoto=findViewById(R.id.ivFoto)
        txtNama=findViewById(R.id.tvNama)
        txtEmail=findViewById(R.id.tvEmail)
        txtJurusan=findViewById(R.id.tvJurusan)
        txtSemester=findViewById(R.id.tvSemester)

        val intent = intent

        val foto=intent.getStringExtra("key_foto")
        val nama=intent.getStringExtra("key_nama")
        val email=intent.getStringExtra("key_email")
        val jurusan=intent.getStringExtra("key_jurusan")
        val semester=intent.getStringExtra("key_semester")

        if (foto != null) {
            ivFoto.setImageResource(foto.toInt())
        }
        txtNama.text=nama
        txtEmail.text=email
        txtJurusan.text=jurusan
        txtSemester.text=semester
    }
}