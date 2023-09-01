package com.startup.tugas_5_eureka.ui.activities.ubah_buku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_5_eureka.repository.Repository
import kotlinx.coroutines.launch

class UbahBukuViewModel(private val repository: Repository) : ViewModel() {
    fun editBook(idBuku: String,linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String){
        repository.editBook(idBuku,linkFotoBuku,judulBuku,penerbitBuku,tahunTerbitBuku,kategoriBuku)
    }
}