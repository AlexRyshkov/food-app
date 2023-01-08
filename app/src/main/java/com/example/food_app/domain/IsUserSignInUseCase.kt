package com.example.animelist.domain

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class IsUserSignInUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    operator fun invoke(): Boolean {
        return firebaseAuth.currentUser !== null
    }
}