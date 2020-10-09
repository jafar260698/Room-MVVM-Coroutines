package com.example.portfolia.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.portfolia.database.PassportEntity.higher_edu
import com.example.portfolia.database.PassportEntity.primary_edu
import com.example.portfolia.database.PassportEntity.secondary_edu
import com.example.portfolia.repository.PassportRepository
import kotlinx.coroutines.launch

class PassportViewModel (val app: Application,
val repository: PassportRepository):AndroidViewModel(app) {

    // primary edu
    fun getPrimary_edu()=repository.getPrimary_edu()

    fun savePrimary_edu(primaryEdu: primary_edu)=viewModelScope.launch {
        repository.insertPrimary_edu(primaryEdu)
    }
    // secondary edu
    fun getSecondary_edu()=repository.getSecondary_edu()

    fun saveSecondary_edu(secondaryEdu: secondary_edu)=viewModelScope.launch {
        repository.insertSecondary_edu(secondaryEdu)
    }
    // higher edu
    fun getHigher_edu()=repository.getHigher_edu()

    fun saveHigher_edu(higherEdu: higher_edu)=viewModelScope.launch {
        repository.insertHigher_edu(higherEdu)
    }

}