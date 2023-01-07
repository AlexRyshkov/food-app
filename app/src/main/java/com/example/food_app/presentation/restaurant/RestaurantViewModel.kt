package com.example.food_app.presentation.restaurant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.domain.GetRestaurantByIdUseCase
import com.example.food_app.data.restaurant.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val getRestaurantByIdUseCase: GetRestaurantByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _restaurant = MutableLiveData<Restaurant>()

    init {
        val id: Long = requireNotNull(savedStateHandle["restaurantId"])
        viewModelScope.launch {
            val restaurant = getRestaurantByIdUseCase(id)
            _restaurant.value = restaurant
        }
    }
}