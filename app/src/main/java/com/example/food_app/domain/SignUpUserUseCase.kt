package com.example.animelist.domain

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class SignUpUserUseCase @Inject constructor(private val firebaseAuth: FirebaseAuth) {
    operator fun invoke(
        email: String,
        password: String,
        onCompleteListener: OnCompleteListener<AuthResult>
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(onCompleteListener)
    }
}