package com.example.lovecalculatorapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoveDao {

    @Insert
    fun insert(loveEntity: LoveEntity)

    @Query("SELECT * FROM love_table")
    fun getAll(): LiveData<List<LoveEntity>>

    @Query("SELECT * FROM love_table WHERE firstName LIKE '%' || :name || '%' OR secondName LIKE '%' || :name || '%'")
    fun getByNames(name: String): LiveData<List<LoveEntity>>

    @Query("SELECT * FROM love_table WHERE percentage LIKE '%'")
    fun getByPercentage(): LiveData<List<LoveEntity>>

    @Delete
    fun deleteLove(loveEntity: LoveEntity)
}