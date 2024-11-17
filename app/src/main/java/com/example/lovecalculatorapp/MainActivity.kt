package com.example.lovecalculatorapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.lovecalculatorapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity() : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val api = RetrofitInstance.api

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loveModel()
    }

    private fun loveModel() = with(binding){
        btnCalculate.setOnClickListener {
            if (edFName.text.toString().isNotEmpty() &&
                edSName.text.toString().isNotEmpty()
            ) {
                api.getPercentage(
                    firstName = edFName.text.toString(),
                    secondName = edSName.text.toString(),
                    key = "63a0d70af1mshaa9f8848c94ee95p13439bjsn9fc173965b94",
                    host = "love-calculator.p.rapidapi.com"
                ).enqueue(object : Callback<LoveModel> {
                    override fun onResponse(p0: Call<LoveModel>, response: Response<LoveModel>) {
                        if (response.isSuccessful && response.body() != null) {
                            val loveModel = response.body()!!
                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            intent.putExtra("percentage", loveModel.percentage)
                            intent.putExtra("result", loveModel.result)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(p0: Call<LoveModel>, p1: Throwable) {
                        Toast.makeText(this@MainActivity, "Ошибка", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this@MainActivity, "Пустой Текст!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}