package com.example.animelist.domain

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class SignOutUserUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    operator fun invoke(
    ) {
        firebaseAuth.signOut()
    }
}