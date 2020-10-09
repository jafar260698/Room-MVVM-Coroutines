package com.example.portfolia.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.portfolia.R
import com.example.portfolia.database.Entity.MyDiary
import com.example.portfolia.database.MainDatabase
import com.example.portfolia.repository.RegisterRepository
import com.example.portfolia.util.Function
import com.example.restaurants.ui.viewmodel.RegisterViewModel
import com.example.restaurants.ui.viewmodel.RegisterViewModelFactory
import kotlinx.android.synthetic.main.activity_save_diary.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class SaveDiary : AppCompatActivity() {
    lateinit var viewModel: RegisterViewModel
    val TAG="SaveDiary"
    var string_spending_time=""
    var string_aspect_language=""
    var string_productivity=""
    var string_were_pro=""

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.parseColor("#ffffff")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_diary)

        val repository= RegisterRepository(MainDatabase(this))
        val viewModelProviderFactory = RegisterViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(RegisterViewModel::class.java)

        val arrayList: ArrayList<String> = ArrayList()
        for (i in 1..36){
            if(i==1) arrayList.add("$i hour")
            else arrayList.add("$i hours")
        }

        val arrayAdapter= ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            arrayList)

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_spending_time!!.adapter = arrayAdapter
        spinner_spending_time!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ){
                val selection = parent.getItemAtPosition(position) as String
                Log.d(TAG, "Selecton $selection")
                if (!TextUtils.isEmpty(selection)) {
                    string_spending_time=selection
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val arrayList2: ArrayList<String> = ArrayList()
        arrayList2.add("Listening")
        arrayList2.add("Reading")
        arrayList2.add("Speaking")
        arrayList2.add("Writing")
        val arrayAdapter2= ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            arrayList2
        )
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_aspect_language!!.adapter = arrayAdapter2
        spinner_aspect_language!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selection = parent.getItemAtPosition(position) as String
                Log.d(TAG, "Selecton $selection")
                if (!TextUtils.isEmpty(selection)) {
                    string_aspect_language=selection
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val arrayList3: ArrayList<String> = ArrayList()
        arrayList3.add("In the morning")
        arrayList3.add("In the afternoon")
        arrayList3.add("In the evening")
        arrayList3.add("at night ")
        val arrayAdapter3= ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            arrayList3
        )
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_productivity!!.adapter = arrayAdapter3
        spinner_productivity!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selection = parent.getItemAtPosition(position) as String
                Log.d(TAG, "Selecton $selection")
                if (!TextUtils.isEmpty(selection)) {
                    string_productivity=selection
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val arrayList4: ArrayList<String> = ArrayList()
        arrayList4.add("At home")
        arrayList4.add("In the library")
        arrayList4.add("In nature")
        arrayList4.add("n the classroom")
        val arrayAdapter4= ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            arrayList4
        )
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_were_prod!!.adapter = arrayAdapter4
        spinner_were_prod!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selection = parent.getItemAtPosition(position) as String
                Log.d(TAG, "Selecton $selection")
                if (!TextUtils.isEmpty(selection)) {
                    string_were_pro=selection
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        save_diary.setOnClickListener {
            if (string_spending_time.isNotEmpty()
                &&string_aspect_language.isNotEmpty()
                &&string_productivity.isNotEmpty()
                &&string_were_pro.isNotEmpty()
                &&methods_diary.text.toString().isNotEmpty()
                &&outcome_diary.text.toString().isNotEmpty()
            ){
                val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss ")
                val currentDateAndTime: String = simpleDateFormat.format(Date())

                val myDiary:MyDiary=MyDiary(
                    string_spending_time,
                    string_aspect_language,
                    string_productivity,
                    string_were_pro,
                    methods_diary.text.toString(),
                    outcome_diary.text.toString(),
                    currentDateAndTime
                )
                viewModel.saveDiary(myDiary)
                Function.showToast(this, "Malumot Saqlandi")
                finish()
            } else Function.showToast(this,"All field should be filled!!!")
        }
    }

}