package com.startup.tugas_5_eureka.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.startup.tugas_5_eureka.model.BukuModel

class FirebaseDataSource {
    private val dbRef = FirebaseDatabase.getInstance().getReference("Books")

    fun getListBooks() : LiveData<Hasil<List<BukuModel>>> {
        val liveData = MutableLiveData<Hasil<List<BukuModel>>>()
        liveData.value = Hasil.Loading
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listBuku = mutableListOf<BukuModel>()
                for (dataSnapshot in snapshot.children){
                    val buku = dataSnapshot.getValue(BukuModel::class.java)
                    if (buku != null) {
                        listBuku.add(buku)
                    }
                }
                Log.d("ANDI LIST",listBuku.toString())
                if (listBuku.isEmpty()){
                    liveData.value = Hasil.Empty
                } else{
                    liveData.value = Hasil.Success(listBuku)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                liveData.value = Hasil.Error(error.message)
                Log.e("ANDI","database error")
            }
        })
        return liveData
    }

    fun getDetailBook(idBuku: String) : LiveData<Hasil<BukuModel>>{
        val liveData = MutableLiveData<Hasil<BukuModel>>()
        liveData.value = Hasil.Loading
        dbRef.child(idBuku).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val buku = snapshot.getValue(BukuModel::class.java)
                buku?.let {
                    liveData.value = Hasil.Success(it)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                liveData.value = Hasil.Error(error.message)
                Log.e("ANDI","database error")
            }
        })
        return liveData
    }

    fun addBook( linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String){
        val idBuku = dbRef.push().key!!
        val buku = BukuModel(idBuku,linkFotoBuku,judulBuku,penerbitBuku,tahunTerbitBuku,kategoriBuku)
        dbRef.child(idBuku).setValue(buku)
    }

    fun editBook(idBuku: String, linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String){
        val buku = BukuModel(idBuku, linkFotoBuku, judulBuku, penerbitBuku, tahunTerbitBuku, kategoriBuku)
        dbRef.child(idBuku).setValue(buku)
    }

    fun deleteBook(idBuku: String){
        dbRef.child(idBuku).removeValue()
    }
}