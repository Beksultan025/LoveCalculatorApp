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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loveDataObserve()
        binding.btnTryAgain.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    fun loveDataObserve() = with(binding) {
        val data = intent.getSerializableExtra(ARG_LOVE_MODEL_KEY) as LoveModel
        tvYouMe.text = "${data.firstName} and ${data.secondName}"
        tvPercentage.text = "${data.percentage}%"
        tvResult.text = data.result
    }

    companion object {
        const val ARG_LOVE_MODEL_KEY = "love_model_key"
    }
}