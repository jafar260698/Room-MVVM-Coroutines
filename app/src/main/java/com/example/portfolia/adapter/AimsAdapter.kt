package com.example.portfolia.adapter

import android.content.Context
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.portfolia.R
import com.example.portfolia.database.Entity.MyAims
import kotlinx.android.synthetic.main.item_aims.view.*

class AimsAdapter(var context: Context,var list: List<MyAims>):
    RecyclerView.Adapter<AimsAdapter.AimsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AimsViewHolder {
      return AimsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_aims,parent,false))
    }

    override fun onBindViewHolder(holder: AimsViewHolder, position: Int) {
        val aims:MyAims=list[position]
        holder.aims_language.text=context.getString(R.string.language)+"${aims.language}"
        holder.aims_reason_for_learning.text=context.getString(R.string.reason_for_learning)+"\n"+aims.reason_of_learning
        holder.aims_exact_thing.text=context.getString(R.string.exact_thing)+"\n"+aims.exact_thing
        holder.aims_cef_level.text=context.getString(R.string.cef_level)+"\n"+aims.cef_level
        holder.aims_purpose_language.text=context.getString(R.string.purpose_language)+"\n"+aims.purpose_language
    }

    override fun getItemCount(): Int {return list.size}

    class AimsViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val aims_language=itemview.aims_language
        val aims_reason_for_learning=itemview.aims_reason_for_learning
        val aims_exact_thing=itemview.aims_exact_thing
        val aims_cef_level=itemview.aims_cef_level
        val aims_purpose_language=itemview.aims_purpose_language
    }

}