package com.example.food_app.presentation.main

import android.content.Context.MODE_PRIVATE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.animelist.domain.CheckUserAuthUseCase
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val checkUserAuthUseCase: CheckUserAuthUseCase): ViewModel() {
    fun isUserAuthorized(jwt: String): Boolean {
        return checkUserAuthUseCase(jwt)
    }
}