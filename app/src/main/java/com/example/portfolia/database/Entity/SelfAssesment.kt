package com.example.portfolia.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "self_assessment")
data class SelfAssesment(
    val listening:String,
    val reading:String,
    val spoken_interaction:String,
    val spoken_production:String,
    val writing:String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}