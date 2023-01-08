package com.example.food_app.presentation.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.food_app.R
import com.example.food_app.databinding.FragmentSignInBinding
import com.example.food_app.presentation.signUp.SignUpFragment
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

        binding.signUpButton.setOnClickListener {
            val ft: FragmentTransaction =
                requireActivity().getSupportFragmentManager().beginTransaction()
            val fragment = SignUpFragment()
            ft.replace(R.id.navHostFragment, fragment)
            ft.commit()
        }
        binding.signInButton.setOnClickListener {
            signInViewModel.trySignIn(
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }




        signInViewModel.signInState.observe(viewLifecycleOwner) {
            if (it == SignInState.SUCCESS) {
                Navigation.findNavController(view).navigate(R.id.homeFragment)
            } else {
                val toast =
                    Toast.makeText(requireActivity().applicationContext, "Sign in error", 3000)
                toast.show()
            }
        }
    }
}