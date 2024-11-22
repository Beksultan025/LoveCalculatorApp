package com.example.lovecalculatorapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.lovecalculatorapp.databinding.ActivityMainBinding
import com.example.lovecalculatorapp.utils.clearTextAndFocus
import com.example.lovecalculatorapp.utils.getTexts

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[LoveViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loveModel()
    }

    private fun loveModel() = with(binding) {
        viewModel.isProgressVisible.observe(this@MainActivity) {
            progressCircular.isVisible = it
        }

        btnCalculate.setOnClickListener {
            viewModel.getPercentage(
                etFirstName.getTexts(),
                etSecondName.getTexts(),
                this@MainActivity,
                SecondActivity()
            )
            etFirstName.clearTextAndFocus()
            etSecondName.clearTextAndFocus()
        }
    }
}