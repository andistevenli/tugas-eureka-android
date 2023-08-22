package com.startup.tugasday2nomor6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList: ArrayList<Profil>):RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return  ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvFoto.setImageResource(currentItem.foto)
        holder.rvNama.text = currentItem.nama
        holder.rvEmail.text=currentItem.email
        holder.rvJurusan.text=currentItem.jurusan
        holder.rvSemester.text =currentItem.semester
    }

    override fun getItemCount(): Int {
        return  dataList.size
    }

    class ViewHolderClass(itemView: View):RecyclerView.ViewHolder(itemView) {
        val rvFoto: ImageView=itemView.findViewById(R.id.id_foto)
        val rvNama: TextView =itemView.findViewById(R.id.id_nama)
        val rvEmail: TextView=itemView.findViewById(R.id.id_email)
        val rvJurusan: TextView=itemView.findViewById(R.id.id_jurusan)
        val rvSemester: TextView=itemView.findViewById(R.id.id_semester)
    }
}