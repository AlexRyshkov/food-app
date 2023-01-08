package com.example.food_app.data.restaurant

import androidx.room.*
import com.example.food_app.data.food.Food
import com.example.food_app.data.relations.RestaurantFood

@Entity
data class Restaurant(
    @PrimaryKey
    val id: Long,
    val name: String,
    val deliveryTime: String,
    val image: Int,
    val rating: Double,
)

data class RestaurantWithFood(
    @Embedded
    val restaurant: Restaurant,
    @Relation(
        parentColumn = "id",
        entity = Food::class,
        entityColumn = "id",
        associateBy = Junction(
            value = RestaurantFood::class,
            parentColumn = "restaurantId",
            entityColumn = "foodId"
        )
    )
    val food: List<Food>
)