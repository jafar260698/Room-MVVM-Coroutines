package com.example.restaurants.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.portfolia.repository.RegisterRepository

class RegisterViewModelFactory(  val app:Application,val repository: RegisterRepository)
    :ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return RegisterViewModel(app,repository) as T
    }
}