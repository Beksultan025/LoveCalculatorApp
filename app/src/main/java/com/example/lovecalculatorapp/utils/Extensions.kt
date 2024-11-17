package com.example.lovecalculatorapp.utils

import android.widget.EditText

fun EditText.clearTextAndFocus(){
    clearFocus()
    text.clear()
}

fun EditText.getTexts() = text.toString()