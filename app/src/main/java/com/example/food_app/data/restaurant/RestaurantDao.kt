package com.example.food_app.data.food

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.food_app.data.restaurant.Restaurant
import com.example.food_app.data.restaurant.RestaurantWithFood

@Dao
interface RestaurantDao {
    @Query("select * from Restaurant")
    fun getAll(): List<Restaurant>

    @Query("select * from Restaurant where id=:id")
    fun get(id: Long): Restaurant

    @Transaction
    @Query("SELECT * FROM Restaurant where id=:id")
    fun getRestaurantWithFood(id: Long): RestaurantWithFood
}