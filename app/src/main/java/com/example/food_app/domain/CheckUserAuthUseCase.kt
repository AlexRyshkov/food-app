package com.example.animelist.domain

import com.example.food_app.data.food.RestaurantDao
import com.example.food_app.data.restaurant.Restaurant
import javax.inject.Inject

class CheckUserAuthUseCase @Inject constructor() {
    operator fun invoke(jwt: String): Boolean {
        return true
    }
}