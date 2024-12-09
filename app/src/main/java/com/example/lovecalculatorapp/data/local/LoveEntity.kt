package com.example.lovecalculatorapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

const val DEFAULT_ID = 0

@Entity(tableName = "love_table")
data class LoveEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = DEFAULT_ID,
    val firstName: String,
    val secondName: String,
    val percentage: Int,
    val result: String
)