package com.dicoding.asclepius.view.history

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.database.entity.SavedResult
import com.dicoding.asclepius.data.database.helper.ViewModelFactory
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.databinding.ToolbarBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryBinding
    private lateinit var toolbarBinding : ToolbarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbarBinding = binding.toolbar
        toolbarBinding.toolbarTitle.setText(R.string.saved_result)

        val historyViewModel = obtainViewModel(this@HistoryActivity)

        historyViewModel.getAllSavedResult().observe(this){
            getFav(it)
        }
    }

    private fun getFav(result: List<SavedResult>?) {
        val adapter = HistoryAdapter()
        adapter.getSavedResult(result)
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
    }

    private fun obtainViewModel(activity: AppCompatActivity): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[HistoryViewModel::class.java]
    }
}