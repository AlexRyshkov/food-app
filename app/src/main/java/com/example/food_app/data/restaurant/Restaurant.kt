package com.example.food_app.data.restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Restaurant(
    @PrimaryKey
    val id: Long,
    val name: String,
    val deliveryTime: String,
    val image: Int,
    val rating: Double,
)