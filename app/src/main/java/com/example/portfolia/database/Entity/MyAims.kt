package com.example.portfolia.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aims")
data class MyAims(
    val language:String,
    val reason_of_learning:String,
    val exact_thing:String,
    val cef_level:String,
    val purpose_language:String) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}