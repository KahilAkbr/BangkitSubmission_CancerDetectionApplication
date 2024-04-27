package com.dicoding.asclepius.view.result

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.view.home.HomeActivity

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

        val analyzeResult = intent.getStringExtra(EXTRA_RESULT)
        binding.resultText.text = analyzeResult

        binding.btnToHome.setOnClickListener { backToHome() }
    }

    private fun backToHome() {
        finishAffinity()

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
    }
}