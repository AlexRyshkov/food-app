package com.example.animelist.domain

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class SignInUserUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    operator fun invoke(
        email: String,
        password: String,
        onCompleteListener: OnCompleteListener<AuthResult>
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(onCompleteListener)
    }
}