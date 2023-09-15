package com.startup.tugas_5_eureka.adapters

import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.startup.tugas_5_eureka.R
import com.startup.tugas_5_eureka.databinding.ItemBukuBinding
import com.startup.tugas_5_eureka.model.BukuModel
import com.startup.tugas_5_eureka.ui.activities.detail_buku.DetailBukuActivity

class DaftarBukuAdapter(private var myList : List<BukuModel>) : RecyclerView.Adapter<DaftarBukuAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemBukuBinding, val context: Context) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemBukuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myList[position]
        Picasso.get().load(currentItem.linkCoverBuku).placeholder(R.drawable.ic_baseline_image_not_supported_24).into(holder.binding.ivFotoBuku)
        holder.binding.tvJudulBuku.text=currentItem.judulBuku
        holder.binding.tvPenulisBuku.text=currentItem.penerbitBuku
        holder.itemView.setOnClickListener{
            val intentToDetailBuku = Intent(holder.context, DetailBukuActivity::class.java)
            intentToDetailBuku.putExtra(DetailBukuActivity.EXTRA_ID_BUKU, currentItem.idBuku)
            it.context.startActivity(intentToDetailBuku)
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}