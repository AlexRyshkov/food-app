package com.example.food_app.presentation.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.animelist.domain.SignUpUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

enum class SignUpState {
    SUCCESS,
    ERROR,
}

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUserUseCase: SignUpUserUseCase) :
    ViewModel() {
    private var _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> get() = _signUpState

    fun trySignUp(email: String, password: String) {
        signUpUserUseCase(email, password) {
            if (it.isSuccessful) {
                _signUpState.value = SignUpState.SUCCESS
            } else {
                _signUpState.value = SignUpState.ERROR
            }
        }
    }
}