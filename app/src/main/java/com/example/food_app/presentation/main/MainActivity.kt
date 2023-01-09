package com.example.food_app.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.food_app.HomeFragment
import com.example.food_app.R
import com.example.food_app.presentation.signIn.SignInFragment
import com.example.food_app.presentation.signUp.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint

fun replaceFragment(supportFragmentManager: FragmentManager, fragment: Fragment) {
    val ft: FragmentTransaction =
        supportFragmentManager.beginTransaction()
    ft.replace(R.id.navHostFragment, fragment)
    ft.commit()
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.action_homeFragment_to_startFragment)
    }
}