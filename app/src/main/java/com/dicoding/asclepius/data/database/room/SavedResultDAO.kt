package com.dicoding.asclepius.data.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.asclepius.data.database.entity.SavedResult

@Dao
interface SavedResultDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert (savedResult: SavedResult)

    @Query("SELECT * FROM SavedResult ORDER BY id ASC")
    fun getAllSavedResult() : LiveData<List<SavedResult>>
}