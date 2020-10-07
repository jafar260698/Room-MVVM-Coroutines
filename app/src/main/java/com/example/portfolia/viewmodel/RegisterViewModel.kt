package com.example.restaurants.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.edmodo.util.Resource
import com.example.portfolia.database.Entity.Certificate
import com.example.portfolia.database.Entity.MyAims
import com.example.portfolia.database.Entity.RegistrationEntity
import com.example.portfolia.database.Entity.Writing
import com.example.portfolia.repository.RegisterRepository
import kotlinx.coroutines.launch

class RegisterViewModel(
    val app:Application,
    val repository:RegisterRepository)
    :AndroidViewModel(app) {

    val register:MutableLiveData<Resource<List<RegistrationEntity>>> = MutableLiveData()

    fun saveRegistration(registrationEntity: RegistrationEntity)=viewModelScope.launch {
        repository.insertReg(registrationEntity)
    }
    fun deleteReg()=viewModelScope.launch {
        repository.deleteRegister()
    }

    fun updateRegister(registrationEntity: RegistrationEntity)=viewModelScope.launch {
        repository.updateRegister(registrationEntity) }

    fun getRegistration()=repository.getRegister()

    // Certificate
    fun saveCertificate(certificate: Certificate)=viewModelScope.launch {
        repository.insertCertificate(certificate)
    }
    fun getCertificate()=repository.getCertificate()
    // Writing
    fun saveWriting(writing: Writing)=viewModelScope.launch {
        repository.saveWriting(writing)
    }
    fun getWriting()=repository.getWriting()

    // aims
    fun getAims()=repository.getAims()
    fun saveAims(myAims: MyAims)=viewModelScope.launch {repository.savetAim(myAims)}

    // diary
    fun getDiary()=repository.getAlldiary()
}