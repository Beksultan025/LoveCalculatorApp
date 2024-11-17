package com.example.lovecalculatorapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.lovecalculatorapp.databinding.ActivityMainBinding
import com.example.lovecalculatorapp.utils.clearTextAndFocus
import com.example.lovecalculatorapp.utils.getTexts

class MainActivity : AppCompatActivity(), LoveContract {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val presenter = MainPresenter(this, SecondActivity())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter.attachLoveContract(this)
        loveModel()
    }

    private fun loveModel() = with(binding) {
        btnCalculate.setOnClickListener {
            presenter.getPercentage(etFirstName.getTexts(), etSecondName.getTexts())
            etFirstName.clearTextAndFocus()
            etSecondName.clearTextAndFocus()
        }
    }

    override fun toastFailure(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }

    override fun toastEmptyText(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }

    override fun isProgressVisible(progress: Boolean) {
        binding.progressCircular.isVisible = progress
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachLoveContract()
    }
}