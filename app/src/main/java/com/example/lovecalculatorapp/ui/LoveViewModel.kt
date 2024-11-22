package com.example.lovecalculatorapp.ui

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovecalculatorapp.data.LoveApiService
import com.example.lovecalculatorapp.data.LoveModel
import com.example.lovecalculatorapp.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoveViewModel @Inject constructor(
    private val retrofit : LoveApiService
) : ViewModel() {

    var isProgressVisible = MutableLiveData(false)

    fun getPercentage(
        firstName: String,
        secondName: String,
        firstActivity: Activity,
        secondActivity: Activity
    ) {
        if (firstName.isEmpty() || secondName.isEmpty()) {
            Toast.makeText(firstActivity, "Text is Empty !", Toast.LENGTH_SHORT).show()
        } else {
            isProgressVisible.value = true
            retrofit.getPercentage(firstName, secondName, Constant.API_KEY, Constant.HOST)
                .enqueue(object : Callback<LoveModel> {
                    override fun onResponse(p0: Call<LoveModel>, response: Response<LoveModel>) {
                        isProgressVisible.value = false
                        if (response.isSuccessful && response.body() != null) {
                            val intent = Intent(firstActivity, secondActivity::class.java)
                            intent.putExtra(SecondActivity.ARG_LOVE_MODEL_KEY, response.body())
                            firstActivity.startActivity(intent)
                        }
                    }

                    override fun onFailure(p0: Call<LoveModel>, p1: Throwable) {
                        isProgressVisible.value = true
                        Toast.makeText(firstActivity, "Error !", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }
}