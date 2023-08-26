package com.startup.tugas_4_eureka.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.startup.tugas_4_eureka.R
import com.startup.tugas_4_eureka.databinding.ActivityMainBinding
import com.startup.tugas_4_eureka.databinding.RecyclerViewItemBinding
import com.startup.tugas_4_eureka.model.GithubUsers
import com.startup.tugas_4_eureka.screens.activities.detail_user.DetailUser

class GithubUsersAdapter():
    RecyclerView.Adapter<GithubUsersAdapter.MyViewHolder>() {

    private var myList = emptyList<GithubUsers>()

    inner class MyViewHolder(val binding : RecyclerViewItemBinding,  var context: Context):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MyViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(myList[position].avatar_url).into(holder.binding.ivFotoProfil)
        holder.binding.tvUsername.text = myList[position].login
        holder.itemView.setOnClickListener {
            val intentToDetailUser = Intent(holder.context,DetailUser::class.java)
            intentToDetailUser.putExtra("username", holder.binding.tvUsername.text)
            it.context.startActivity(intentToDetailUser)
        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<GithubUsers>){
        myList = newList
        notifyDataSetChanged()
    }
}