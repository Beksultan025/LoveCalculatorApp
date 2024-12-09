package com.example.lovecalculatorapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.lovecalculatorapp.databinding.ActivityMainBinding
import com.example.lovecalculatorapp.utils.clearTextAndFocus
import com.example.lovecalculatorapp.utils.getTexts
import com.example.lovecalculatorapp.utils.toastData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val loveViewModel: LoveViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setViewObserves()
        setOnClickListener()
    }

    private fun setViewObserves() = with(loveViewModel) {
        isProgressVisible.observe(this@MainActivity) { visibility ->
            binding.progressCircular.isVisible = visibility
        }

        loveToastData.observe(this@MainActivity) { message ->
            toastData(message)
        }

        loveData.observe(this@MainActivity) { data ->
            startActivity(
                Intent(
                    this@MainActivity,
                    SecondActivity::class.java
                ).apply {
                    putExtra(SecondActivity.ARG_LOVE_MODEL_KEY, data)
                }
            )
        }
    }

    private fun setOnClickListener() = with(binding) {
        btnCalculate.setOnClickListener {
            loveViewModel.getPercentage(
                etFirstName.getTexts(),
                etSecondName.getTexts(),
            )
            etFirstName.clearTextAndFocus()
            etSecondName.clearTextAndFocus()
        }
    }
}