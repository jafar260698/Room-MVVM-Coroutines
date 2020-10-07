package com.example.portfolia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolia.R
import com.example.portfolia.database.Entity.Certificate
import kotlinx.android.synthetic.main.item_certificate.view.*

class CertificateAdapter(var context: Context, var list:List<Certificate>,var listener: OnItemClickListener)
    :RecyclerView.Adapter<CertificateAdapter.CertificateViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CertificateViewHolder {
        return CertificateViewHolder(LayoutInflater.from(context).inflate(R.layout.item_certificate,parent,false))
    }

    override fun onBindViewHolder(holder: CertificateViewHolder, position: Int) {
        val certificate:Certificate=list[position]
        holder.item_type_work.text=certificate.type_of_work
        holder.item_level.text="Level: ${certificate.level}"
        holder.item_date_obtained.text=certificate.date_of_obtained
        holder.initialize(certificate,listener)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    class CertificateViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
      val item_type_work=itemview.item_type_work
      val item_level=itemview.item_level
      val item_date_obtained=itemview.item_date_obtained

      fun initialize(item:Certificate,action:OnItemClickListener){
          itemView.setOnClickListener {
              action.onItemClick(item,adapterPosition)
          }
       }
    }

    interface OnItemClickListener{
        fun onItemClick(item: Certificate, position: Int)
    }

}