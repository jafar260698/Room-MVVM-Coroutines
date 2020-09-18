package com.example.portfolia.util

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import com.example.portfolia.R


class LoadingDialog(var activity: Activity) {
    private lateinit var alertDialog: AlertDialog

    fun startLoading(){
        val builder= AlertDialog.Builder(activity)
        val layoutInflater: LayoutInflater =activity.layoutInflater
        builder.setView(layoutInflater.inflate(R.layout.custom_layout,null))
        builder.setCancelable(false)
        alertDialog=builder.create()
        alertDialog.show()
    }

    fun dismissDialog(){
        alertDialog.dismiss()
    }
}