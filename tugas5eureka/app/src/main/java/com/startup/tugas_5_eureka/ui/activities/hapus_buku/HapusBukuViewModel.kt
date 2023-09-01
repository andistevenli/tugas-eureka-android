package com.startup.tugas_5_eureka.ui.activities.hapus_buku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_5_eureka.repository.Repository
import kotlinx.coroutines.launch

class HapusBukuViewModel(private val repository: Repository): ViewModel() {
    fun deleteBook(idBuku: String){
        repository.deleteBook(idBuku)
    }
}