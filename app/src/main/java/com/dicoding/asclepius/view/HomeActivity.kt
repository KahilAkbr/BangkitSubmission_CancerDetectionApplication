package com.dicoding.asclepius.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityHomeBinding
import com.dicoding.asclepius.databinding.CardHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var cardBinding : CardHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardBinding = binding.cardHome

        cardBinding.btnDetect.setOnClickListener { moveToAnalyze() }
    }

    private fun moveToAnalyze() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}