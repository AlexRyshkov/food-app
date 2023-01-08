package com.example.food_app.presentation.signIn

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist.domain.SignInState
import com.example.animelist.domain.SignInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.prefs.Preferences
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    val signInUserUseCase: SignInUserUseCase,
) : ViewModel() {

    private var _signInState = MutableLiveData<SignInState>()
    val signInState get() = _signInState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            signInState.value = SignInState.loading
            val result = signInUserUseCase(email, password)
            _signInState.value = result.signInState
            if (result.signInState == SignInState.success) {
            }
        }
    }
}