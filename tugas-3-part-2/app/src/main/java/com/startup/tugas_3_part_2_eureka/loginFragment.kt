package com.startup.tugas_3_part_2_eureka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class loginFragment : Fragment() {

    private  lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  layoutInflater.inflate(R.layout.fragment_login, container, false)
        communicator = activity as Communicator

        val loginBtn = view.findViewById<Button>(R.id.btnLogin)
        val editTextNama = view.findViewById<EditText>(R.id.etName)
        val editTextEmail = view.findViewById<EditText>(R.id.etEmail)
        val editTextJurusan = view.findViewById<EditText>(R.id.etJurusan)
        val editTextSemester = view.findViewById<EditText>(R.id.etSemester)

        loginBtn.setOnClickListener(){
            communicator.passingData(editTextNama.text.toString(),editTextEmail.text.toString(),editTextJurusan.text.toString(),editTextSemester.text.toString())

        }

        return  view

    }

}