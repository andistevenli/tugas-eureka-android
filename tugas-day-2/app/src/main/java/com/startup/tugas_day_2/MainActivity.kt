package com.startup.tugas_day_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    lateinit var btn_login: Button
    lateinit var et_name: EditText
    lateinit var et_email: EditText
    lateinit var et_jurusan: EditText
    lateinit var et_semester: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et_name=findViewById(R.id.etName)
        et_email=findViewById(R.id.etEmail)
        et_jurusan=findViewById(R.id.etJurusan)
        et_semester=findViewById(R.id.etSemester)
        btn_login=findViewById(R.id.btnLogin)

        var patternEmail:Pattern=Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        var patternAngka:Pattern=Pattern.compile("^\\d+\$")

        btn_login.setOnClickListener(){
            if (TextUtils.isEmpty(et_name.text.toString())||TextUtils.isEmpty(et_email.text.toString())||TextUtils.isEmpty(et_jurusan.text.toString())||TextUtils.isEmpty(et_semester.text.toString())) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                var matchEmail: Matcher=patternEmail.matcher(et_email.text)

                if (matchEmail.matches()){
                    var matchSemester: Matcher=patternAngka.matcher(et_semester.text)

                    if (matchSemester.matches()){
                        val nama = et_name.text.toString()
                        val email=et_email.text.toString()
                        val jurusan=et_jurusan.text.toString()
                        val semester=et_semester.text.toString()

                        val intent = Intent(applicationContext,HomeActivity::class.java)

                        intent.putExtra("key_nama",nama)
                        intent.putExtra("key_email",email)
                        intent.putExtra("key_jurusan",jurusan)
                        intent.putExtra("key_semester",semester)

                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Semester tidak valid", Toast.LENGTH_SHORT).show()
                    }
                } else{
                    Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}