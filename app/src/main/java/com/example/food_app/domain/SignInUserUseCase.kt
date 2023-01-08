package com.example.animelist.domain

import com.example.food_app.data.food.RestaurantDao
import com.example.food_app.data.restaurant.RestaurantWithFood
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.concurrent.thread

enum class SignInState {
    success,
    loading,
    invalidCredentials,
    networkError
}

data class SignInResult(
    val signInState: SignInState,
    val jwt: String,
)

class SignInUserUseCase @Inject constructor() {
    suspend operator fun invoke(email: String, password: String): SignInResult {
        return SignInResult(
            SignInState.success,
            "sadfkojgnadsufoipjgbasfdkjfdab"
        )
    }
}