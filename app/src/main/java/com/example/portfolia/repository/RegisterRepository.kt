package com.example.portfolia.repository

import com.example.portfolia.database.Entity.Certificate
import com.example.portfolia.database.Entity.MyAims
import com.example.portfolia.database.MainDatabase
import com.example.portfolia.database.Entity.RegistrationEntity
import com.example.portfolia.database.Entity.Writing


class RegisterRepository(val db:MainDatabase){

    suspend fun insertReg(registrationEntity: RegistrationEntity)=
        db.getRegister().insert(registrationEntity)

    fun getRegister()= db.getRegister().getRegister()

    suspend fun deleteRegister()=db.getRegister().deleteReg()

    suspend fun updateRegister(registrationEntity: RegistrationEntity)=db.getRegister().updateReg(registrationEntity)
    // Certificate
    suspend fun  insertCertificate(certificate: Certificate)=db.getRegister().insertCertificate(certificate)

    fun getCertificate()=db.getRegister().getCertificate()

    // Writing
    suspend fun saveWriting(writing: Writing)=db.getRegister().insertWriting(writing)
    fun getWriting()=db.getRegister().getWriting()

    // aims
    suspend fun savetAim(myAims: MyAims)=db.getRegister().insertAims(myAims)
    fun getAims()=db.getRegister().getAims()


    // My diary
    fun getAlldiary()=db.getRegister().getAllDiary()
}