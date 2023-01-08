package com.example.food_app.presentation.restaurant

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.food_app.R
import com.example.food_app.databinding.FragmentRestaurantBinding
import com.example.food_app.rvAdapters.FoodRVAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantFragment : Fragment() {
    private var _binding: FragmentRestaurantBinding? = null
    val binding get() = _binding!!
    private val restaurantViewModel: RestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBar.bringToFront()
        binding.restaurantScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            binding.appBar.setBackgroundColor(
                if (scrollY * resources.displayMetrics.density <= 250) Color.TRANSPARENT else resources.getColor(
                    R.color.white
                )
            )
        }
        binding.foodRecyclerView.adapter =
            FoodRVAdapter(restaurantViewModel.restaurant.value?.food!!)
        binding.backImageButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}