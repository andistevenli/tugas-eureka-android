package com.startup.tugas_5_eureka.repository

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import com.startup.tugas_5_eureka.firebase.FirebaseDataSource
import com.startup.tugas_5_eureka.firebase.Hasil
import com.startup.tugas_5_eureka.model.BukuModel
import javax.inject.Inject


class Repository @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource,
) {
    /***
    *   this method is a bridge to get data of list books from firebase data source to show in UI
    *   @author Andi
     *   @since September 15th, 2023
    * */
     fun getListBuku() : LiveData<Hasil<List<BukuModel>>> {
        return firebaseDataSource.getListBooks()
    }

    /***
     *   this method is a bridge to get data of book detail from firebase data source to show in UI
     *   @author Andi
     *   @since September 15th, 2023
     * */
    fun getDetailBuku(idBuku: String) : LiveData<Hasil<BukuModel>> {
        return firebaseDataSource.getDetailBook(idBuku)
    }

    /***
     *   this method is a bridge to add data from user input in UI to firebase data source
     *   @author Andi
     *   @since September 15th, 2023
     * */
    fun addBook(linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String) : LiveData<Hasil<String>> {
        return firebaseDataSource.addBook(linkFotoBuku,judulBuku,penerbitBuku,tahunTerbitBuku,kategoriBuku)
    }
    /***
     *   this method is a bridge to edit data from user input in UI to firebase data source
     *   @author Andi
     *   @since September 15th, 2023
     * */

    fun editBook(idBuku: String, linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String) : LiveData<Hasil<String>> {
        return firebaseDataSource.editBook(idBuku, linkFotoBuku, judulBuku, penerbitBuku, tahunTerbitBuku, kategoriBuku)
    }

    /***
     *   this method is a bridge to delete data on firebase data source
     *   @author Andi
     *   @since September 15th, 2023
     * */
    fun deleteBook(idBuku: String) : LiveData<Hasil<String>> {
        return firebaseDataSource.deleteBook(idBuku)
    }
}