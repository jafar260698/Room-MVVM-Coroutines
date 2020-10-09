package com.example.restaurants.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.portfolia.repository.PassportRepository
import com.example.portfolia.repository.RegisterRepository
import com.example.portfolia.viewmodel.PassportViewModel

class PassportViewModelFactory(val app:Application, val repository: PassportRepository)
    :ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return PassportViewModel(app,repository) as T
    }
}