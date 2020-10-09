package com.example.portfolia.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.portfolia.database.PassportEntity.higher_edu
import com.example.portfolia.database.PassportEntity.primary_edu
import com.example.portfolia.database.PassportEntity.secondary_edu

@Dao
interface PassportDao {

    @Query("SELECT * FROM primary_edu ORDER BY id DESC")
    fun getPrimary_edu() : LiveData<List<primary_edu>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrimary_edu(primaryEdu: primary_edu): Long

    @Query("SELECT * FROM secondary_edu ORDER BY id DESC")
    fun getSecond_edu() : LiveData<List<secondary_edu>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSecond_edu(secondaryEdu: secondary_edu): Long

    @Query("SELECT * FROM higher_edu ORDER BY id DESC")
    fun getHigher_edu() : LiveData<List<higher_edu>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHigher_edu(higherEdu: higher_edu): Long

}