package com.example.portfolia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolia.R
import com.example.portfolia.database.Entity.MyDiary
import kotlinx.android.synthetic.main.item_diary.view.*

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
        holder.amount_of_time.text=listOfData.amount_of_time
        holder.aspect_of_studying.text=listOfData.aspect_of_studying
        holder.when_product.text=listOfData.when_product
        holder.where_product.text=listOfData.where_product
        holder.method_of_study.text=listOfData.method_of_study
        holder.outcome_myresult.text=listOfData.outcome
        holder.time_inserted.text=listOfData.time_inserted
    }

    override fun getItemCount(): Int {
      return list.size
    }

    class DiaryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
      val amount_of_time=itemView.amount_of_time
      val aspect_of_studying=itemView.aspect_of_studying
      val when_product=itemView.when_product
      val where_product=itemView.where_product
      val method_of_study=itemView.method_of_study
      val outcome_myresult=itemView.outcome_myresult
      val time_inserted=itemView.time_inserted

    }

}