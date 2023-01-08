package com.example.food_app.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.food_app.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

//        val preferences = getPreferences(MODE_PRIVATE)
//        val jwt = preferences.getString("AUTH_JWT", null)
//        if (jwt == null || !mainViewModel.isUserAuthorized(jwt)) {
//            val ft = supportFragmentManager.beginTransaction()
//            val fragment = SignInFragment()
//            ft.replace(R.id.navHostFragment, fragment)
//            ft.commit()
//        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            print(1)
        }
    }
}