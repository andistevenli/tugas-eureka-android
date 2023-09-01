package com.startup.tugas_5_eureka.ui.activities.detail_buku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.model.BukuModel
import com.startup.tugas_5_eureka.repository.Repository
import kotlinx.coroutines.launch

class DetailBukuViewModel(private val repository: Repository) : ViewModel() {
    fun getBuku(idBuku: String) : LiveData<Hasil<BukuModel>> {
        return  repository.getDetailBuku(idBuku)
    }
}