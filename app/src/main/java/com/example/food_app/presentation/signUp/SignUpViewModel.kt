package com.example.food_app.presentation.signUp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animelist.domain.SignUpUserUseCase
import com.example.food_app.presentation.signIn.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUserUseCase: SignUpUserUseCase) :
    ViewModel() {
    private var _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun trySignUp(email: String, password: String) {
        try {
            _uiState.value = UiState.Loading
            signUpUserUseCase(email, password).apply {
                addOnCompleteListener {
                    if (it.isSuccessful) {
                        _uiState.value = UiState.Success
                    }
                }
                addOnFailureListener {
                    _uiState.value = UiState.Error(it.message.toString())
                }
            }
        } catch (exception: Exception) {
            Log.e("SIGN_UP", exception.message.toString())
        }
    }
}

