package com.example.portfolia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolia.R
import com.example.portfolia.database.Entity.MyDiary

class DiaryAdapter(var context: Context,var list:List<MyDiary>)
    :RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiaryAdapter.DiaryViewHolder {
        return DiaryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_diary,parent,false))
    }

    override fun onBindViewHolder(holder: DiaryAdapter.DiaryViewHolder, position: Int) {
        val listOfData:MyDiary=list[position]

    }

    override fun getItemCount(): Int {
      return list.size
    }

    class DiaryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

}