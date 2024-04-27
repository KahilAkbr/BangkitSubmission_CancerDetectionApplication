package com.dicoding.asclepius.view.history

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.database.entity.SavedResult
import com.dicoding.asclepius.data.repository.SavedResultRepository

class HistoryViewModel(application: Application) : ViewModel() {
    private val savedResultRepository: SavedResultRepository = SavedResultRepository(application)

    fun getAllSavedResult() : LiveData<List<SavedResult>> = savedResultRepository.getAllSavedResult()
}