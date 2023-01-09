package com.example.food_app.presentation.signIn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animelist.domain.SignInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


enum class SignInState {
    SUCCESS,
    LOADING,
    ERROR,
}

data class SignInResult(
    val signInState: SignInState,
    val errorMessage: String?,
)

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase
) : ViewModel() {
    private var _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun trySignIn(email: String, password: String) {
        try {
            _uiState.value = UiState.Loading
            signInUserUseCase(email, password).apply {
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
            Log.e("SIGN_IN", exception.message.toString())
        }
    }
}

sealed class UiState {
    object Success : UiState()
    object Loading : UiState()
    data class Error(var errorMessage: String) : UiState()
}