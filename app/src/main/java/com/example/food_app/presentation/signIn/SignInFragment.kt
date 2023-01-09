package com.example.food_app.presentation.signIn

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.food_app.R
import com.example.food_app.databinding.FragmentSignInBinding
import com.example.food_app.validateEmailInputText
import com.example.food_app.validatePasswordInputText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    val binding get() = _binding!!

    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        val appProgressBar = requireActivity().findViewById<ProgressBar>(R.id.appProgressBar)
        appProgressBar.bringToFront()

        binding.signUpButton.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.signInButton.setOnClickListener {
            val isValidEmail = validateEmailInputText(binding.emailEditText)
            val isValidPassword = validatePasswordInputText(binding.passwordEditText)

            if (isValidEmail && isValidPassword) {
                signInViewModel.trySignIn(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            }
        }

        binding.emailEditText.addTextChangedListener {
            validateEmailInputText(binding.emailEditText)
        }

        binding.passwordEditText.addTextChangedListener {
            validatePasswordInputText(binding.passwordEditText)
        }

        signInViewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    appProgressBar.visibility = View.VISIBLE
                }
                is UiState.Error -> {
                    binding.authErrorTextView.text = it.errorMessage
                }
                is UiState.Success -> {
                    findNavController().navigate(R.id.action_authGraph_toHomeFragment)
                }
            }
        }
    }
}