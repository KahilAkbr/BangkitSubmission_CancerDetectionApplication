package com.dicoding.asclepius.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.asclepius.data.database.entity.SavedResult
import com.dicoding.asclepius.data.database.room.SavedResultDAO
import com.dicoding.asclepius.data.database.room.SavedResultRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SavedResultRepository(application: Application) {
    private val savedResultDAO: SavedResultDAO
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SavedResultRoomDatabase.getDatabase(application)
        savedResultDAO = db.savedResultDAO()
    }
    fun insert(savedResult: SavedResult) {
        executorService.execute { savedResultDAO.insert(savedResult) }
    }

    fun getAllSavedResult() : LiveData<List<SavedResult>> = savedResultDAO.getAllSavedResult()


}