package com.dicoding.asclepius.data.database.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.asclepius.data.database.entity.SavedResult

class SavedDiffCallback(private val oldSavedList: List<SavedResult>, private val newSavedList: List<SavedResult>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldSavedList.size
    }

    override fun getNewListSize(): Int {
        return newSavedList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldSavedList[oldItemPosition].id == newSavedList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFav = oldSavedList[oldItemPosition]
        val newFav = newSavedList[newItemPosition]
        return oldFav.imageUrl == newFav.imageUrl && oldFav.prediction == newFav.prediction && oldFav.score == newFav.score
    }
}