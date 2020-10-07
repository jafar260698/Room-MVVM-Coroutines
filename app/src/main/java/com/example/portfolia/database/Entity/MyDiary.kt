package com.example.portfolia.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class MyDiary(
    val amount_of_time:String,
    val aspect_of_studying:String,
    val when_product:String,
    val where_product:String,
    val method_of_study:String,
    val outcome:String,
    val time_inserted:String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}