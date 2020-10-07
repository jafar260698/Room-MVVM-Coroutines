package com.example.portfolia.database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "register_table")
data class RegistrationEntity(
    var name:String,
    var date_of_birth:String,
    var place_of_birth:String,
    var nationality:String,
    var mother_tongue:String,
    var date_using:String,
    var image_uri:String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}