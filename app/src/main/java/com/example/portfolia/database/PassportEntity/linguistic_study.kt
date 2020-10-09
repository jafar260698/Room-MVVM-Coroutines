package com.example.portfolia.database.PassportEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "linguistic_study")
data class linguistic_study(
    val language:String,
    val details:String,
    val from:String,
    val to:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}