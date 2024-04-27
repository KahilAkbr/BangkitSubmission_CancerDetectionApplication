package com.dicoding.asclepius.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class SavedResult(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var imageUrl: String? = null,

    var prediction: String = "",

    var score: String = "",

) : Parcelable