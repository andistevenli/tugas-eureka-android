package com.startup.tugas_5_eureka.ui.activities.tambah_buku

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_5_eureka.model.BukuModel
import com.startup.tugas_5_eureka.repository.Repository
import kotlinx.coroutines.launch

class TambahBukuViewModel(private val repository: Repository) : ViewModel() {
    fun addBook(linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String){
        repository.addBook(linkFotoBuku,judulBuku,penerbitBuku,tahunTerbitBuku,kategoriBuku)
    }
}