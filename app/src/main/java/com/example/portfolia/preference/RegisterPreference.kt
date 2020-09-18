package com.example.restaurants.ui.preference

import android.content.Context
import android.content.SharedPreferences
import com.example.portfolia.R

class RegisterPreference(context: Context) {
    private val sharedPreference=context.getSharedPreferences(R.string.restaurant_file.toString(),Context.MODE_PRIVATE)

    fun setRegistration(key:String,boolean: Boolean){
        sharedPreference.edit().putBoolean(key,boolean).apply()
    }

    fun getRegistration(key: String):Boolean?{return sharedPreference.getBoolean(key,false)}
}