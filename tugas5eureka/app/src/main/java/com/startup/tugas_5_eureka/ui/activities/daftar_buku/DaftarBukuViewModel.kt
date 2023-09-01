package com.startup.tugas_5_eureka.ui.activities.daftar_buku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.model.BukuModel
import com.startup.tugas_5_eureka.repository.Repository
import kotlinx.coroutines.launch

class DaftarBukuViewModel(private val repository: Repository) : ViewModel() {
    val listBuku: LiveData<Hasil<List<BukuModel>>> = repository.getListBuku()
}