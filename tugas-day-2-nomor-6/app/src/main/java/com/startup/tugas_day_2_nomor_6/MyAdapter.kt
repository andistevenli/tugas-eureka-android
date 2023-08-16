package com.startup.tugas_day_2_nomor_6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private  val data:ArrayList<Profil>):RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return  data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater= LayoutInflater.from(parent.context)
        return ViewHolder(inflater,parent)
    }
}