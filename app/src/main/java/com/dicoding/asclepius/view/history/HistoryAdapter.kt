package com.dicoding.asclepius.view.history

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.database.entity.SavedResult
import com.dicoding.asclepius.data.database.helper.SavedDiffCallback
import com.dicoding.asclepius.databinding.ItemSavedBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ListViewHolder>() {
    private var savedResultList = listOf<SavedResult>()
    inner class ListViewHolder(private val binding: ItemSavedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(savedResult: SavedResult) {
            with(binding) {
                tvResultTitle.text = "Hasil Analisis: "
                Glide.with(root.context)
                    .load(savedResult.imageUrl)
                    .into(imgItemPhoto)
                tvResult.text = "${savedResult.prediction} ${savedResult.score}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemSavedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return savedResultList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(savedResultList[position])
    }

    fun getSavedResult(newList:List<SavedResult>?) {
        val diffCallback = SavedDiffCallback(savedResultList, newList ?: emptyList())
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.savedResultList = newList ?: emptyList()
        diffResult.dispatchUpdatesTo(this)
    }
}