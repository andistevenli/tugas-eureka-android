package com.startup.bukuku.screen.activities.daftar_buku

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.firebase.database.*
import com.kennyc.view.MultiStateView
import com.startup.bukuku.adapters.DaftarBukuAdapter
import com.startup.bukuku.databinding.ActivityDaftarBukuBinding
import com.startup.bukuku.model.BukuModel
import com.startup.bukuku.screen.activities.detail_buku.DetailBukuActivity

class DaftarBukuViewModel: ViewModel(), MultiStateView.StateListener {
    private lateinit var bindingDaftarBuku: ActivityDaftarBukuBinding
    private var dbRef: DatabaseReference  = FirebaseDatabase.getInstance().getReference("Books")
    private var liveData = MutableLiveData<ArrayList<BukuModel>>()
    private lateinit var listBuku: ArrayList<BukuModel>
    private lateinit var multiStateView: MultiStateView

    fun init(context: Context){
        bindingDaftarBuku = ActivityDaftarBukuBinding.inflate(LayoutInflater.from(context))
        //set layout nya
        bindingDaftarBuku.rvDaftarBuku.layoutManager = LinearLayoutManager(context)
        bindingDaftarBuku.rvDaftarBuku.setHasFixedSize(true)
        //deklarasi tipe data dari list buku nya
        listBuku = arrayListOf<BukuModel>()
        liveData.value = listBuku
        //deklarasi multi state view
        multiStateView = bindingDaftarBuku.stateDaftarBuku
        multiStateView.listener = this
        //set first state of multi state view
        multiStateView.viewState = MultiStateView.ViewState.LOADING
    }

    open fun getDataBuku(context: Context){
        init(context)
        if (liveData.value == null){
            multiStateView.viewState = MultiStateView.ViewState.EMPTY
        } else {
            dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    listBuku.clear()
                    if (snapshot.exists()){
                        for (bukuSnap in snapshot.children){
                            val dataBuku = bukuSnap.getValue(BukuModel::class.java)
                            listBuku.add(dataBuku!!)
                        }
                        multiStateView.viewState = MultiStateView.ViewState.CONTENT
                        //set adapternya
                        bindingDaftarBuku.rvDaftarBuku.adapter = DaftarBukuAdapter(liveData.value!!)

                    }

                    DaftarBukuAdapter(liveData.value!!).setOnItemClickListener(object : DaftarBukuAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intentToDetailBuku = Intent(context,
                                DetailBukuActivity::class.java)
                            intentToDetailBuku.putExtra("idBuku",listBuku[position].idBuku)
                            intentToDetailBuku.putExtra("linkFotoBuku",listBuku[position].linkFotoBuku)
                            intentToDetailBuku.putExtra("judulBuku",listBuku[position].judulBuku)
                            intentToDetailBuku.putExtra("penerbitBuku",listBuku[position].penerbitBuku)
                            intentToDetailBuku.putExtra("tahunTerbitBuku",listBuku[position].tahunTerbitBuku)
                            intentToDetailBuku.putExtra("kategoriBuku",listBuku[position].kategoriBuku)
                            startActivity(context,intentToDetailBuku,null)
                        }
                    })
                }
                override fun onCancelled(error: DatabaseError) {
                    multiStateView.viewState = MultiStateView.ViewState.ERROR
                    Log.e("ERROR","gagal mengambil data")
                }
            })
        }
    }

    override fun onStateChanged(viewState: MultiStateView.ViewState) {
        Log.v("MSVSample", "onStateChanged; viewState: $viewState")
    }
}