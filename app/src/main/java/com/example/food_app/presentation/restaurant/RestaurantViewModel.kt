package com.example.food_app.presentation.restaurant

import androidx.lifecycle.*
import com.example.animelist.domain.GetRestaurantByIdUseCase
import com.example.food_app.data.restaurant.RestaurantWithFood
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val getRestaurantByIdUseCase: GetRestaurantByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _restaurant = MutableLiveData<RestaurantWithFood>()
    val restaurant get():LiveData<RestaurantWithFood> = _restaurant

    init {
        val id: Long = requireNotNull(savedStateHandle["restaurantId"])
        viewModelScope.launch {
            val restaurant = getRestaurantByIdUseCase(id)
            _restaurant.value = restaurant
        }
    }
}