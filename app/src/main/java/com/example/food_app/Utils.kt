package com.example.food_app

import android.util.Patterns
import com.google.android.material.textfield.TextInputEditText

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun CharSequence?.isValidPassword() = !isNullOrEmpty() && this.length > 6

fun validateEmailInputText(emailEditText: TextInputEditText): Boolean {
    if (!emailEditText.text.isValidEmail()) {
        emailEditText.error = "invalid email"
        return false
    }
    return true
}

fun validatePasswordInputText(passwordEditText: TextInputEditText): Boolean {
    if (!passwordEditText.text.isValidPassword()) {
        passwordEditText.error = "short password"
        return false
    }
    return true
}


fun validateConfirmPasswordInputText(
    password: String,
    confirmPasswordEditText: TextInputEditText
): Boolean {
    if (!confirmPasswordEditText.text.toString().equals(password)) {
        confirmPasswordEditText.error = "passwords do not match"
        return false
    }
    return true
}