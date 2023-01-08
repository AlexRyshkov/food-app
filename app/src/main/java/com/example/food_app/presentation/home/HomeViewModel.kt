package com.example.food_app.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.domain.GetRestaurantsUseCase
import com.example.food_app.data.restaurant.Restaurant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getRestaurantsUseCase: GetRestaurantsUseCase) :
    ViewModel() {
    private val _restaurants = MutableLiveData<List<Restaurant>?>(null)
    val restaurants get() = _restaurants

    init {
        viewModelScope.launch {
            val restaurants = getRestaurantsUseCase()
            _restaurants.value = restaurants
        }
    }
}