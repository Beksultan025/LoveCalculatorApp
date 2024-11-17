package com.example.lovecalculatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lovecalculatorapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val percentage = intent.getStringExtra("percentage")
        val result = intent.getStringExtra("result")

        binding.tvPercentage.text = percentage
        binding.tvResult.text = result

        binding.btnAgain.setOnClickListener {
            finish()
        }
    }
}