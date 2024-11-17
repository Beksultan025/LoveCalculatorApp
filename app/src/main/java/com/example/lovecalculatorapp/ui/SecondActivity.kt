package com.example.lovecalculatorapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lovecalculatorapp.data.LoveModel
import com.example.lovecalculatorapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data = intent.getSerializableExtra(ARG_LOVE_MODEL_KEY) as LoveModel

        binding.tvYouMe.text = "${data.firstName} and ${data.secondName}"
        binding.tvPercentage.text = "${data.percentage}%"
        binding.tvResult.text = data.result

        binding.btnTryAgain.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val ARG_LOVE_MODEL_KEY = "love_model_key"
    }
}