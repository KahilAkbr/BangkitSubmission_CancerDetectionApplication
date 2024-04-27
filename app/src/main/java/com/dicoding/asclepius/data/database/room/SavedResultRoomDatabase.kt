package com.dicoding.asclepius.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.asclepius.data.database.entity.SavedResult

@Database(entities = [SavedResult::class], version = 1)
abstract class SavedResultRoomDatabase : RoomDatabase() {
    abstract fun savedResultDAO() : SavedResultDAO
    companion object{
        @Volatile
        private var INSTANCE: SavedResultRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): SavedResultRoomDatabase {
            if (INSTANCE == null) {
                synchronized(SavedResultRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SavedResultRoomDatabase::class.java, "saved_result")
                        .build()
                }
            }
            return INSTANCE as SavedResultRoomDatabase
        }
    }
}