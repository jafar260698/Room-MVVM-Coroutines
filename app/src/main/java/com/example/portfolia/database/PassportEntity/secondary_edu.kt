package com.example.portfolia.database.PassportEntity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "secondary_edu")
data class secondary_edu(
    val language:String,
    val details:String,
    val from:String,
    val to:String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}