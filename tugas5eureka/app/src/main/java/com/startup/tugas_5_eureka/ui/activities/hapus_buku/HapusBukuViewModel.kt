package com.startup.tugas_5_eureka.ui.activities.hapus_buku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HapusBukuViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    private val _result = MediatorLiveData<Hasil<String>>()
    val result: LiveData<Hasil<String>> get() = _result

    /***
     *   This method is to delete book that connected to repository
     *   @param idBuku to get the book id to delete the book
     *   @author Andi
     *   @since September 15th, 2023
     * */
    fun deleteBook(idBuku: String){
        val data = repository.deleteBook(idBuku)
        _result.addSource(data) {
            _result.postValue(it)
        }
    }
}