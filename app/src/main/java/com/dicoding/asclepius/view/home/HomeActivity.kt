package com.dicoding.asclepius.view.home

import android.content.Intent
import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.response.ArticlesItem
import com.dicoding.asclepius.databinding.ActivityHomeBinding
import com.dicoding.asclepius.databinding.CardHomeBinding
import com.dicoding.asclepius.view.history.HistoryActivity
import com.dicoding.asclepius.view.main.MainActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var cardBinding : CardHomeBinding
    private lateinit var viewModel: NewsViewModel


    private val adapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.purplePrimary)))

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNews.addItemDecoration(itemDecoration)

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        cardBinding = binding.cardHome

        cardBinding.btnDetect.setOnClickListener { moveToAnalyze() }
        cardBinding.btnResult.setOnClickListener { moveToHistory() }

        observeNews()

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNews.addItemDecoration(itemDecoration)

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        cardBinding = binding.cardHome

        cardBinding.btnDetect.setOnClickListener { moveToAnalyze() }
        cardBinding.btnResult.setOnClickListener { moveToHistory() }

        observeNews()

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun observeNews(){
        viewModel.news.observe(this) { news ->
            val filteredNewsList = news?.filter {
                it.url != null &&
                        it.title != null &&
                        it.urlToImage != null &&
                        it.description != null
            }
            getNews(filteredNewsList)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun getNews(news: List<ArticlesItem>?) {
        adapter.submitList(news)
        binding.rvNews.adapter = adapter
    }

    private fun moveToAnalyze() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun moveToHistory(){
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }
}