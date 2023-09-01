package com.startup.tugas_5_eureka.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.startup.tugas_5_eureka.firebase.FirebaseDataSource
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.model.BukuModel

class Repository {
    private val firebaseDataSource: FirebaseDataSource = FirebaseDataSource()

     fun getListBuku() : LiveData<Hasil<List<BukuModel>>> {
        return firebaseDataSource.getListBooks()
    }

    fun getDetailBuku(idBuku: String) : LiveData<Hasil<BukuModel>> {
        return firebaseDataSource.getDetailBook(idBuku)
    }

    fun addBook(linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String){
        return firebaseDataSource.addBook(linkFotoBuku,judulBuku,penerbitBuku,tahunTerbitBuku,kategoriBuku)
    }

    fun editBook(idBuku: String, linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String){
        return firebaseDataSource.editBook(idBuku, linkFotoBuku, judulBuku, penerbitBuku, tahunTerbitBuku, kategoriBuku)
    }

    fun deleteBook(idBuku: String){
        return firebaseDataSource.deleteBook(idBuku)
    }
}