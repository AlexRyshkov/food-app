package com.example.food_app.presentation.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animelist.domain.SignInUserUseCase
import com.example.food_app.presentation.signUp.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


enum class SignInState {
    SUCCESS,
    ERROR,
}

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase
) : ViewModel() {
    private var _signInState = MutableLiveData<SignInState>()
    val signInState: LiveData<SignInState> get() = _signInState

    fun trySignIn(email: String, password: String) {
        signInUserUseCase(email, password) {
            if (it.isSuccessful) {
                _signInState.value = SignInState.SUCCESS
            } else {
                _signInState.value = SignInState.ERROR
            }
        }
    }
}