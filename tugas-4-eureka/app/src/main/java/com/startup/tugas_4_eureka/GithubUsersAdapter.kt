package com.startup.tugas_4_eureka

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GithubUsersAdapter(private val githubUsersDataList: ArrayList<GithubUsers>):
    RecyclerView.Adapter<GithubUsersAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val fotoProfil: ImageView=itemView.findViewById(R.id.ivFotoProfil)
        val username: TextView=itemView.findViewById(R.id.tvUsername)
        val userDetail: CardView = itemView.findViewById(R.id.cvUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(githubUsersDataList[position].avatar_url).into(holder.fotoProfil)
        holder.username.text = githubUsersDataList[position].login
        holder.userDetail.setOnClickListener(){

        }
    }

    override fun getItemCount(): Int {
        return githubUsersDataList.size
    }

    fun setData(data: ArrayList<GithubUsers>){
        githubUsersDataList.clear()
        githubUsersDataList.addAll(data)
        notifyDataSetChanged()
    }
}