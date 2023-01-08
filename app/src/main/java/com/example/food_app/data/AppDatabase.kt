package com.example.food_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.food_app.data.food.Food
import com.example.food_app.data.food.FoodDao
import com.example.food_app.data.food.RestaurantDao
import com.example.food_app.data.relations.RestaurantFood
import com.example.food_app.data.restaurant.Restaurant

@Database(entities = [Food::class, Restaurant::class, RestaurantFood::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun restaurantDao(): RestaurantDao
}