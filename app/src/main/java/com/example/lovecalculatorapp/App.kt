package com.example.lovecalculatorapp

import android.app.Application
import androidx.room.Room
import com.example.lovecalculatorapp.data.local.LoveDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        var loveDataBase: LoveDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        getInstance()
    }

    fun getInstance(): LoveDataBase? {
        if (loveDataBase == null) {
            loveDataBase = applicationContext.let {
                Room.databaseBuilder(
                    it,
                    LoveDataBase::class.java,
                    "love.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return loveDataBase
    }
}