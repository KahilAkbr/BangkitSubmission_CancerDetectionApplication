package com.dicoding.asclepius.view.result

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.asclepius.data.database.entity.SavedResult
import com.dicoding.asclepius.data.database.room.SavedResultRoomDatabase
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.view.home.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        imageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.resultImage.setImageURI(it)
        }

        val labelResult = intent.getStringExtra(EXTRA_LABEL)
        val scoreResult = intent.getStringExtra(EXTRA_SCORE)
        val finalResult = "$labelResult: $scoreResult"
        binding.resultText.text = finalResult

        binding.btnToHome.setOnClickListener { backToHome() }

        binding.btnSave.setOnClickListener {
            val savedResult = SavedResult(imageUrl = imageUri.toString(), prediction = labelResult.toString(), score = scoreResult.toString())
            insertToDatabase(savedResult)
        }
    }

    private fun insertToDatabase(savedResult: SavedResult) {
        val savedResultDao = SavedResultRoomDatabase.getDatabase(this).savedResultDAO()
        CoroutineScope(Dispatchers.IO).launch {
            savedResultDao.insert(savedResult)
        }
    }

    private fun backToHome() {
        finishAffinity()

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_LABEL = "extra_label"
        const val EXTRA_SCORE = "extra_score"
    }
}