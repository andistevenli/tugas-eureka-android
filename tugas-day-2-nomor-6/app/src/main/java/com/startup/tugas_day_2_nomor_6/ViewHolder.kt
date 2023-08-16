package com.startup.tugas_day_2_nomor_6

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(inflater: LayoutInflater,parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_view_template,parent,false)) {

    private var imgView: ImageView?=null
    private var txtNama: TextView?=null
    private var txtEmail: TextView?=null
    private  var txtJurusan: TextView?=null
    private  var  txtSemester: TextView?=null

    init{
        imgView=itemView.findViewById(R.id.img_view)
        txtNama=itemView.findViewById(R.id.tvNama)
        txtEmail=itemView.findViewById(R.id.tvEmail)
        txtJurusan=itemView.findViewById(R.id.tvJurusan)
        txtSemester=itemView.findViewById(R.id.tvSemester)
    }

    fun bind( data:Profil){
        imgView?.setImageResource(data.imgView)
        txtNama?.text=data.nama
        txtEmail?.text=data.email
        txtJurusan?.text=data.jurusan
        txtSemester?.text=data.semester

    }
}