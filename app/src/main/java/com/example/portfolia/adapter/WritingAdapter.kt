package com.example.portfolia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolia.R
import com.example.portfolia.database.Entity.Writing
import kotlinx.android.synthetic.main.item_certificate.view.*
import kotlinx.android.synthetic.main.item_writing.view.*

class WritingAdapter(var context: Context, var list:List<Writing>,var clicklidtrnrt:OnItemClickListener)
    :RecyclerView.Adapter<WritingAdapter.WritingViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WritingViewHolder {
        return WritingViewHolder(LayoutInflater.from(context).inflate(R.layout.item_writing,parent,false))}

    override fun onBindViewHolder(holder: WritingViewHolder, position: Int) {
        val writing: Writing =list[position]
        holder.item_date_obtained.text=writing.date_of_obtained
        holder.item_level.text="Level: ${writing.level}"
        holder.item_type_work.text=writing.type_of_work
        holder.initialize(writing,clicklidtrnrt)
    }
    override fun getItemCount(): Int {
        return list.size
    }

    class WritingViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val item_type_work=itemview.item_type_work_writing
        val item_level=itemview.item_level_writing
        val item_date_obtained=itemview.item_date_obtained_writing

        fun initialize(item: Writing, action:OnItemClickListener){
            itemView.setOnClickListener {
                action.onItemClick(item,adapterPosition)
            }
        }

    }

    interface OnItemClickListener{
        fun onItemClick(item: Writing, position: Int)
    }
}
