package com.example.portfolia.repository

import com.example.portfolia.database.MainDatabase
import com.example.portfolia.database.PassportEntity.higher_edu
import com.example.portfolia.database.PassportEntity.primary_edu
import com.example.portfolia.database.PassportEntity.secondary_edu

class PassportRepository(val db: MainDatabase) {

    fun getPrimary_edu()=db.getPassport().getPrimary_edu()

    suspend fun insertPrimary_edu(primaryEdu: primary_edu)=db.getPassport().insertPrimary_edu(primaryEdu)

    fun getSecondary_edu()=db.getPassport().getSecond_edu()

    suspend fun insertSecondary_edu(secondaryEdu: secondary_edu)=db.getPassport().insertSecond_edu(secondaryEdu)

    fun getHigher_edu()=db.getPassport().getHigher_edu()

    suspend fun insertHigher_edu(higherEdu: higher_edu)=db.getPassport().insertHigher_edu(higherEdu)

}