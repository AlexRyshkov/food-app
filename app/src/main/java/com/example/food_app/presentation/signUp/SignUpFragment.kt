package com.example.food_app.presentation.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.food_app.*
import com.example.food_app.databinding.FragmentSignUpBinding
import com.example.food_app.presentation.main.replaceFragment
import com.example.food_app.presentation.signIn.SignInFragment
import com.example.food_app.presentation.signIn.UiState
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

        val appProgressBar = requireActivity().findViewById<ProgressBar>(R.id.appProgressBar)
        appProgressBar.bringToFront()

        binding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.emailEditText.addTextChangedListener {
            validateEmailInputText(binding.emailEditText)
        }

        binding.passwordEditText.addTextChangedListener {
            validatePasswordInputText(binding.passwordEditText)
        }

        binding.confirmPasswordEditText.addTextChangedListener {
            validateConfirmPasswordInputText(
                binding.passwordEditText.text.toString(),
                binding.confirmPasswordEditText
            )
        }

        signUpViewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    appProgressBar.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    appProgressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_authGraph_toHomeFragment)
                }
                is UiState.Error -> {
                    appProgressBar.visibility = View.GONE
                    binding.errorTextView.text = it.errorMessage
                }
            }
        }

        binding.signUpButton.setOnClickListener {
            val isValidEmail = validateEmailInputText(binding.emailEditText)
            val isValidPassword = validatePasswordInputText(binding.passwordEditText)
            val arePasswordsMatch = validateConfirmPasswordInputText(
                binding.passwordEditText.text.toString(),
                binding.confirmPasswordEditText
            )

            if (isValidEmail && isValidPassword && arePasswordsMatch) {
                signUpViewModel.trySignUp(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            }
        }
    }
}