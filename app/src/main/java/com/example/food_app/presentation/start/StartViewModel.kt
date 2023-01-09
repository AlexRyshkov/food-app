package com.example.food_app.presentation.start

import androidx.lifecycle.ViewModel
import com.example.animelist.domain.IsUserSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(private val isUserSignInUseCase: IsUserSignInUseCase): ViewModel() {
    fun isUserAuthorized(): Boolean {
        return isUserSignInUseCase()
    }
}