package com.example.lovecalculatorapp.ui

import android.app.Activity
import android.content.Intent
import com.example.lovecalculatorapp.data.LoveModel
import com.example.lovecalculatorapp.data.RetrofitInstance
import com.example.lovecalculatorapp.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val firstActivity: Activity,
    private val secondActivity: Activity) {

    private val api = RetrofitInstance.api
    private var loveContract: LoveContract? = null

    fun attachLoveContract(loveContract: LoveContract) {
        this.loveContract = loveContract
    }

    fun getPercentage(
        firstName: String,
        secondName: String
    ) {
        if (firstName.isEmpty() || secondName.isEmpty()) {
            loveContract?.toastEmptyText("EditText is empty!")
            return
        }
        loveContract?.isProgressVisible(true)
        api.getPercentage(
            firstName = firstName,
            secondName = secondName,
            key = Constant.API_KEY,
            host = Constant.HOST,

        ).enqueue(object : Callback<LoveModel> {
            override fun onResponse(p0: Call<LoveModel>, response: Response<LoveModel>) {
                if (response.isSuccessful && response.body() != null) {
                    val loveModel = response.body()
                    val intent = Intent(firstActivity, secondActivity::class.java)
                    intent.putExtra(SecondActivity.ARG_LOVE_MODEL_KEY , loveModel)
                    firstActivity.startActivity(intent)
                    loveContract?.isProgressVisible(false)
                }
            }

            override fun onFailure(p0: Call<LoveModel>, p1: Throwable) {
                loveContract?.isProgressVisible(false)
                loveContract?.toastFailure("Error!")
            }

        })
    }

    fun detachLoveContract() {
        loveContract = null
    }
}