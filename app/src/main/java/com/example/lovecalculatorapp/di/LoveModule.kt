package com.example.lovecalculatorapp.di

import com.example.lovecalculatorapp.App
import com.example.lovecalculatorapp.data.local.LoveDataBase
import com.example.lovecalculatorapp.data.network.LoveApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object LoveModule {

    @Provides
    fun getLoveApiService(): LoveApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://love-calculator.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(LoveApiService::class.java)
    }

    @Provides
    fun getLoveDataBase(): LoveDataBase {
        return App().getInstance()!!
    }
}