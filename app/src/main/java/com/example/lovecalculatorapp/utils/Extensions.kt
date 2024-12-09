package com.example.lovecalculatorapp.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun EditText.clearTextAndFocus() {
    clearFocus()
    text.clear()
}

fun EditText.getTexts() = text.toString()

fun Context.toastData(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}