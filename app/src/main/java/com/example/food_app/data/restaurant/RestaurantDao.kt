package com.example.food_app.data.food

import androidx.room.Dao
import androidx.room.Query
import com.example.food_app.data.restaurant.Restaurant

@Dao
interface RestaurantDao {
    @Query("select * from Restaurant")
    fun getAll(): List<Restaurant>

    @Query("select * from Restaurant where id=:id")
    fun get(id: Long): Restaurant
}