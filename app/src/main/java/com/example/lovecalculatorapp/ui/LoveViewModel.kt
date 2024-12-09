package com.example.lovecalculatorapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovecalculatorapp.data.local.LoveDataBase
import com.example.lovecalculatorapp.data.local.LoveEntity
import com.example.lovecalculatorapp.data.network.LoveApiService
import com.example.lovecalculatorapp.data.network.LoveModel
import com.example.lovecalculatorapp.utils.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoveViewModel @Inject constructor(
    private val api: LoveApiService,
    private val loveDataBase: LoveDataBase
) : ViewModel() {

    val isProgressVisible = MutableLiveData(false)
    val loveToastData = MutableLiveData<String>()
    val loveData = MutableLiveData<LoveModel>()

    fun getPercentage(
        firstName: String,
        secondName: String
    ) {
        if (firstName.isEmpty() || secondName.isEmpty()) {
            loveToastData.value = "Text is empty!"
        } else {
            isProgressVisible.value = true
            api.getPercentage(firstName, secondName, Constant.API_KEY, Constant.HOST)
                .enqueue(object : Callback<LoveModel> {
                    override fun onResponse(p0: Call<LoveModel>, response: Response<LoveModel>) {
                        isProgressVisible.value = false
                        if (response.isSuccessful && response.body() != null) {
                            val data = response.body()!!
                            loveDataBase.loveDao().insert(
                                LoveEntity(
                                    firstName = data.firstName,
                                    secondName = data.secondName,
                                    percentage = data.percentage.toInt(),
                                    result = data.result
                                )
                            )
                            loveData.value = data
                        }
                    }

                    override fun onFailure(p0: Call<LoveModel>, p1: Throwable) {
                        isProgressVisible.value = false
                        loveToastData.value = "Error :$p1"
                    }
                })
        }
    }
}