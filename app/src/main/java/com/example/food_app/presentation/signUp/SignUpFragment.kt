package com.example.food_app.presentation.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.food_app.R
import com.example.food_app.presentation.signIn.SignInFragment
import com.example.food_app.databinding.FragmentCreateAccountBinding

class SignUpFragment : Fragment() {
    private var _binding : FragmentCreateAccountBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
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
    }
}