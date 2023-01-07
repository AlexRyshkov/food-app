package com.example.animelist.domain

import com.example.food_app.data.food.RestaurantDao
import com.example.food_app.data.restaurant.Restaurant
import javax.inject.Inject

class GetRestaurantByIdUseCase @Inject constructor(private val restaurantDao: RestaurantDao) {
    operator fun invoke(id:Long): Restaurant {
        return restaurantDao.get(id)
    }
}