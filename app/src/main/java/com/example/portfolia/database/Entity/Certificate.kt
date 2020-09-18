package com.example.portfolia.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "certificate")
data class Certificate(
    var type_of_work:String,
    var level:String,
    var date_of_obtained:String,
    var file_uri:String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}