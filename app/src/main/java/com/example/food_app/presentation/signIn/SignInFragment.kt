package com.example.food_app.presentation.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.animelist.domain.SignInState
import com.example.food_app.HomeFragment
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

        binding.createAccountButton.setOnClickListener {
            val ft: FragmentTransaction =
                requireActivity().getSupportFragmentManager().beginTransaction()
            val fragment = SignUpFragment()
            ft.replace(R.id.navHostFragment, fragment)
            ft.commit()
        }
        binding.signInButton.setOnClickListener {
            signInViewModel.signIn(
                binding.emailTextView.text.toString(),
                binding.passwordTextView.text.toString()
            )
        }

        signInViewModel.signInState.observe(viewLifecycleOwner) {
            val progressBar = requireActivity().findViewById<ProgressBar>(R.id.appProgressBar)
            progressBar.visibility = View.VISIBLE
            if (it == SignInState.loading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                if (it == SignInState.success) {
                    val ft = requireActivity().supportFragmentManager.beginTransaction()
                    val fragment = HomeFragment()
                    ft.replace(R.id.navHostFragment, fragment)
                    ft.commit()
                }
            }
        }
    }
}