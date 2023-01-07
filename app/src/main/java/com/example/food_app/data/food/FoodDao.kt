package com.example.food_app.data.food

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("select * from Food")
    fun getAll(): List<Food>
}