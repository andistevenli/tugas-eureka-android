package com.startup.bukuku.screen.fragments.detail_buku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.startup.bukuku.R
import com.startup.bukuku.databinding.FragmentEditBukuBinding

class EditBukuFragment : Fragment() {

    private var _bindingEditBuku: FragmentEditBukuBinding? = null
    private val bindingEditBuku get() = _bindingEditBuku!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingEditBuku = FragmentEditBukuBinding.inflate(inflater,container,false)
        return bindingEditBuku.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingEditBuku = null
    }
}