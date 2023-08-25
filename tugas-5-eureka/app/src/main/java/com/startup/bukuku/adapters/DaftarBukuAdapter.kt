package com.startup.bukuku.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.startup.bukuku.R
import com.startup.bukuku.databinding.ItemBukuBinding
import com.startup.bukuku.model.BukuModel

class DaftarBukuAdapter(private val dataBuku: ArrayList<BukuModel>) : RecyclerView.Adapter<DaftarBukuAdapter.MyViewHolder>() {

    private lateinit var myListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        myListener = clickListener
    }

    class MyViewHolder(val binding: ItemBukuBinding, clicklistener: onItemClickListener) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener(){
                clicklistener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBukuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MyViewHolder(binding,myListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataBuku[position]
        Picasso.get().load(currentItem.linkFotoBuku).placeholder(R.drawable.ic_baseline_image_24).into(holder.binding.ivFotoBuku)
        holder.binding.tvJudulBuku.text=currentItem.judulBuku
        holder.binding.tvPenulisBuku.text=currentItem.penerbitBuku
    }

    override fun getItemCount(): Int {
        return dataBuku.size
    }
}