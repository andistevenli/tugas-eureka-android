package com.startup.tugas_3_part_2_eureka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class homeFragment : Fragment() {
    var displayName: String? = ""
    var displayEmail: String? = ""
    var displayJurusan: String? = ""
    var displaySemester: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)

        displayName=arguments?.getString("nama")
        displayEmail=arguments?.getString("email")
        displayJurusan=arguments?.getString("jurusan")
        displaySemester=arguments?.getString("semester")

        val textViewNama = view.findViewById<TextView>(R.id.tvNama)
        val textViewEmail = view.findViewById<TextView>(R.id.tvEmail)
        val textViewJurusan = view.findViewById<TextView>(R.id.tvJurusan)
        val textViewSemester = view.findViewById<TextView>(R.id.tvSemester)

        textViewNama.text=displayName
        textViewEmail.text=displayEmail
        textViewJurusan.text=displayJurusan
        textViewSemester.text=displaySemester

        return  view
    }

}