package com.example.food_app.presentation.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import com.example.food_app.HomeFragment
import com.example.food_app.R
import com.example.food_app.databinding.FragmentSignUpBinding
import com.example.food_app.presentation.signIn.SignInFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInButton.setOnClickListener {
            val ft: FragmentTransaction =
                requireActivity().getSupportFragmentManager().beginTransaction()
            val fragment = SignInFragment()
            ft.replace(R.id.navHostFragment, fragment)
            ft.commit()
        }

        signUpViewModel.signUpState.observe(viewLifecycleOwner) {
            if (it == SignUpState.SUCCESS) {
                navigateHome()
            }
        }

        binding.signUpButton.setOnClickListener {
            signUpViewModel.trySignUp(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    private fun navigateHome() {
        val ft = requireActivity().supportFragmentManager.beginTransaction()
        ft.replace(R.id.navHostFragment, HomeFragment())
        ft.commit()
    }
}