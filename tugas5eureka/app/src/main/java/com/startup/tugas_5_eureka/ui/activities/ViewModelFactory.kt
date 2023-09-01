package com.startup.tugas_5_eureka.ui.activities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.startup.tugas_5_eureka.repository.Repository
import com.startup.tugas_5_eureka.ui.activities.daftar_buku.DaftarBukuViewModel
import com.startup.tugas_5_eureka.ui.activities.detail_buku.DetailBukuViewModel
import com.startup.tugas_5_eureka.ui.activities.hapus_buku.HapusBukuViewModel
import com.startup.tugas_5_eureka.ui.activities.tambah_buku.TambahBukuViewModel
import com.startup.tugas_5_eureka.ui.activities.ubah_buku.UbahBukuViewModel

class ViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DaftarBukuViewModel::class.java)) {
            return DaftarBukuViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(TambahBukuViewModel::class.java)){
            return TambahBukuViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(UbahBukuViewModel::class.java)){
            return UbahBukuViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailBukuViewModel::class.java)){
            return DetailBukuViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HapusBukuViewModel::class.java)){
            return HapusBukuViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}