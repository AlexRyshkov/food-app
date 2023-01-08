package com.example.food_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.food_app.data.restaurant.Restaurant
import com.example.food_app.databinding.FragmentHomeBinding
import com.example.food_app.presentation.home.HomeViewModel
import com.example.food_app.rvAdapters.FoodFilterItem
import com.example.food_app.rvAdapters.FoodFilterRvAdapter
import com.example.food_app.rvAdapters.MarginItemDecoration
import com.example.food_app.rvAdapters.RestaurantsRVAdapter
import dagger.hilt.android.AndroidEntryPoint


val foodFilterList = listOf(
    FoodFilterItem("pizza", R.drawable.pizza_filter),
    FoodFilterItem("salad", R.drawable.salad_filter),
    FoodFilterItem("sushi", R.drawable.sushi_filter),
    FoodFilterItem("burger", R.drawable.cheeseburger_filter),
    FoodFilterItem("meat", R.drawable.meat_filter),
)

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val restaurantClickListener: (Restaurant) -> Unit = {
            val bundle = Bundle()
            bundle.putLong("restaurantId", it.id)
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_restaurantFragment, bundle)
        }

        val restaurantsRV = binding.restaurantsRecyclerView
        val foodFilterRV = binding.foodFilterRV
        foodFilterRV.addItemDecoration(
            MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin))
        )
        foodFilterRV.adapter = FoodFilterRvAdapter(foodFilterList)
        homeViewModel.restaurants.observe(viewLifecycleOwner) {
            if (it !== null) {
                restaurantsRV.adapter = RestaurantsRVAdapter(it, restaurantClickListener)
            }
        }
        binding.restaurantsCountTextView.text =
            "${homeViewModel.restaurants.value?.size} Restaurants"

        binding.profileImageButton.setOnClickListener {
            homeViewModel.signOut()
            findNavController().navigate(R.id.signInFragment)
        }
    }
}