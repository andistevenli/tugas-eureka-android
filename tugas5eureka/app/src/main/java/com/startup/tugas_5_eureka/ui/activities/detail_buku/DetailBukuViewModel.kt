package com.startup.tugas_5_eureka.ui.activities.detail_buku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.model.BukuModel
import com.startup.tugas_5_eureka.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailBukuViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    /***
    *   This method is to get book detail that connected to repository
     *   @param idBuku to get the book id
     *   @author Andi
     *   @since September 15th, 2023
    * */
    fun getBuku(idBuku: String) : LiveData<Hasil<BukuModel>> {
        return  repository.getDetailBuku(idBuku)
    }
}