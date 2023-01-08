package com.example.food_app.data.relations

import androidx.room.Entity

@Entity(primaryKeys = ["restaurantId", "foodId"])
data class RestaurantFood(
    val restaurantId: Long,
    val foodId: Long
)