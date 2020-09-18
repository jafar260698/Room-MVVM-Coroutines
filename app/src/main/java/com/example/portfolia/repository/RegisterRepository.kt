package com.example.portfolia.repository

import com.example.portfolia.database.Entity.Certificate
import com.example.portfolia.database.MainDatabase
import com.example.portfolia.database.Entity.RegistrationEntity


class RegisterRepository(val db:MainDatabase){

    suspend fun insertReg(registrationEntity: RegistrationEntity)=
        db.getRegister().insert(registrationEntity)

    fun getRegister()= db.getRegister().getRegister()

    suspend fun  insertCertificate(certificate: Certificate)=db.getRegister().insertCertificate(certificate)

    fun getCertificate()=db.getRegister().getCertificate()

}