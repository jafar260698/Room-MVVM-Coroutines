package com.example.portfolia.repository

import com.example.portfolia.database.Entity.Certificate
import com.example.portfolia.database.MainDatabase

class CertificateRepository(val db:MainDatabase) {

    suspend fun  insertCertificate(certificate: Certificate)=db.getRegister().insertCertificate(certificate)

    fun getCertificate()=db.getRegister().getCertificate()

}