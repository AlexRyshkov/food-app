package com.example.food_app.data.food

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey
    val id: Long,
    val name: String,
    val description: String,
    val rating: Double,
    val price: Double,
    val image: Int,
)