package com.startup.tugas_5_eureka.ui.activities.tambah_buku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
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
class TambahBukuViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _result = MediatorLiveData<Hasil<String>>()
    val result: LiveData<Hasil<String>> get() = _result

    /***
     *   This method is to add book that connected to repository
     *   @param linkFotoBuku to get the book cover link
     *   @param judulBuku to get the title of the book
     *   @param penerbitBuku to get the book publisher
     *   @param tahunTerbitBuku to get the the year of when the book is published
     *   @author Andi
     *   @since September 15th, 2023
     * */
    fun addBook(linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String){
        val data = repository.addBook(linkFotoBuku,judulBuku,penerbitBuku,tahunTerbitBuku,kategoriBuku)
        _result.addSource(data) {
            _result.postValue(it)
        }
    }
}