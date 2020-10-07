package com.example.portfolia.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.portfolia.database.Entity.*

@Dao
interface RegisterDao {
    // Registration
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(registrationEntity: RegistrationEntity): Long

    @Query("SELECT * FROM register_table ORDER BY id DESC LIMIT 1")
    fun getRegister(): LiveData<List<RegistrationEntity>>

    @Query("DELETE FROM register_table")
    suspend fun deleteReg()

    @Update
    suspend fun updateReg(vararg registrationEntity: RegistrationEntity)

    // Certificate
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCertificate(certificate: Certificate): Long

    @Query("SELECT *FROM certificate ORDER BY id DESC")
    fun getCertificate(): LiveData<List<Certificate>>

    @Delete
    suspend fun deleteCertificate(certificate: Certificate)

    // Writing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWriting(writing: Writing): Long

    @Query("SELECT *FROM writing ORDER BY id DESC")
    fun getWriting(): LiveData<List<Writing>>

    @Delete
    suspend fun deleteWriting(writing: Writing)

    // aims
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAims(aims: MyAims): Long

    @Query("SELECT *FROM aims ORDER BY id DESC")
    fun getAims(): LiveData<List<MyAims>>

    // My Diary
    @Query("SELECT * FROM diary ORDER BY id DESC")
    fun getAllDiary() :LiveData<List<MyDiary>>

}