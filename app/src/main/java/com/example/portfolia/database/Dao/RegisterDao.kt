package com.example.portfolia.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.portfolia.database.Entity.Certificate
import com.example.portfolia.database.Entity.RegistrationEntity

@Dao
interface RegisterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(registrationEntity: RegistrationEntity): Long

    @Query("SELECT * FROM register_table ORDER BY id DESC LIMIT 1")
    fun getRegister(): LiveData<List<RegistrationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCertificate(certificate: Certificate): Long

    @Query("SELECT *FROM certificate ORDER BY id DESC")
    fun getCertificate(): LiveData<List<Certificate>>

}