package com.example.portfolia.repository

import com.example.portfolia.database.Entity.*
import com.example.portfolia.database.MainDatabase


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
    suspend fun deleteAims(aims: MyAims)=db.getRegister().deleteAims(aims)

    // My diary
    fun getAlldiary()=db.getRegister().getAllDiary()
    suspend fun saveDiary(myDiary: MyDiary)=db.getRegister().insertDiary(myDiary)

    // Self Assessment
    fun getSelfAssessment()=db.getRegister().getSelfAssessment()
    suspend fun saveSelf_Asses(selfAssesment: SelfAssesment)=db.getRegister().insertSelfAssessment(selfAssesment)
}