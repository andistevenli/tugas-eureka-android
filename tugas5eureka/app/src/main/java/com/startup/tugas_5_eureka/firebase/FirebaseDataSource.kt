package com.startup.tugas_5_eureka.firebase

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.startup.tugas_5_eureka.model.BukuModel

class FirebaseDataSource(private val context: Context) {
    private val dbRef = FirebaseDatabase.getInstance().getReference("Books")


    /***
     *  this method is to get list Books from the firebase realtime DB
     *  @author ANdi
     *  @since September 15th, 2023
    *
    * */
    fun getListBooks() : LiveData<Hasil<List<BukuModel>>> {
        val liveData = MutableLiveData<Hasil<List<BukuModel>>>()
        liveData.value = Hasil.Loading
        if (connectToInternet()){
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val listBuku = mutableListOf<BukuModel>()
                    for (dataSnapshot in snapshot.children){
                        val buku = dataSnapshot.getValue(BukuModel::class.java)
                        if (buku != null) {
                            listBuku.add(buku)
                        }
                    }
                    if (listBuku.isEmpty()){
                        liveData.value = Hasil.Empty
                    } else{
                        liveData.value = Hasil.Success(listBuku)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    liveData.value = Hasil.Error(error.message)
                }
            })
        } else {
            liveData.value = Hasil.Error("Connection Error")
        }
        return liveData
    }

    /***
    *   this method is to get the book details from firebase realtime DB
     *   @author Andi
     *   @since September 15th, 2023
    * */
    fun getDetailBook(idBuku: String) : LiveData<Hasil<BukuModel>>{
        val liveData = MutableLiveData<Hasil<BukuModel>>()
        liveData.value = Hasil.Loading
        if (connectToInternet()){
            dbRef.child(idBuku).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val buku = snapshot.getValue(BukuModel::class.java)
                    buku?.let {
                        liveData.value = Hasil.Success(it)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    liveData.value = Hasil.Error(error.message)
                }
            })
        } else  {
            liveData.value = Hasil.Error("Connection Error")
        }
        return liveData
    }

    /***
    *   this method is to add book from the app to firebase realtime DB
     *   @author Andi
     *   @since September 15th, 2023
    * */
    fun addBook( linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String) : LiveData<Hasil<String>> {
        val liveData = MutableLiveData<Hasil<String>>()
        if (connectToInternet()){
            val idBuku = dbRef.push().key!!
            val buku = BukuModel(idBuku,linkFotoBuku,judulBuku,penerbitBuku,tahunTerbitBuku,kategoriBuku)
            dbRef.child(idBuku).setValue(buku)
        } else {
            liveData.value = Hasil.Error("Connection Error")
        }
        return  liveData
    }

    /***
    *   this method is to edit book from the app to firebase realtime DB
     *   @author Andi
     *   @since September 15th, 2023
    * */
    fun editBook(idBuku: String, linkFotoBuku: String, judulBuku: String, penerbitBuku: String, tahunTerbitBuku: String, kategoriBuku: String) : LiveData<Hasil<String>> {
        val liveData = MutableLiveData<Hasil<String>>()
        if (connectToInternet()){
            val buku = BukuModel(idBuku, linkFotoBuku, judulBuku, penerbitBuku, tahunTerbitBuku, kategoriBuku)
            dbRef.child(idBuku).setValue(buku)
        } else {
            liveData.value = Hasil.Error("Connection Error")
        }
        return  liveData
    }

    /***
    *   this method is to delete book on firebase realtime DB
     *   @author Andi
     *   @since September 15th, 2023
    * */
    fun deleteBook(idBuku: String) : LiveData<Hasil<String>> {
        val liveData = MutableLiveData<Hasil<String>>()
        if (connectToInternet()){
            dbRef.child(idBuku).removeValue()
        } else {
            liveData.value = Hasil.Error("Connection Error")
        }
        return liveData
    }

    /***
     * This method to check user's internet existance
     * @author Andi
     * @since September 15th, 2023
     */
    private fun connectToInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}