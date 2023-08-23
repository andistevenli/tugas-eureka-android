package com.startup.tugas_3_part_2_eureka

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.startup.tugas_3_part_2_eureka.databinding.FragmentLoginBinding

class loginFragment : Fragment() {

    private  lateinit var communicator: Communicator

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        val view =  binding.root
        communicator = activity as Communicator

//        val loginBtn = view.findViewById<Button>(R.id.btnLogin)
//        val editTextNama = view.findViewById<EditText>(R.id.etName)
//        val editTextEmail = view.findViewById<EditText>(R.id.etEmail)
//        val editTextJurusan = view.findViewById<EditText>(R.id.etJurusan)
//        val editTextSemester = view.findViewById<EditText>(R.id.etSemester)



        return  view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener(){
            communicator.passingData(binding.etName.text.toString(),binding.etEmail.text.toString(),binding.etJurusan.text.toString(),binding.etSemester.text.toString())

        }
    }

}