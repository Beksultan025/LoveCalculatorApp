package com.example.lovecalculatorapp.ui

interface LoveContract {
    fun toastFailure(toast : String)
    fun toastEmptyText(toast : String)
    fun isProgressVisible(progress : Boolean)
}