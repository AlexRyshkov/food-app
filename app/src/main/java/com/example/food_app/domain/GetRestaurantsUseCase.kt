package com.example.animelist.domain

import com.example.food_app.data.food.RestaurantDao
import com.example.food_app.data.restaurant.Restaurant
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(private val restaurantDao: RestaurantDao) {

    operator fun invoke(): List<Restaurant> {
        return restaurantDao.getAll()
    }
}