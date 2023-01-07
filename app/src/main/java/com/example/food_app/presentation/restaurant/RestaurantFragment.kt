package com.example.food_app.presentation.restaurant

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.food_app.FoodRVAdapter
import com.example.food_app.R
import com.example.food_app.data.food.Food
import com.example.food_app.databinding.FragmentRestaurantBinding
import dagger.hilt.android.AndroidEntryPoint

val foodList = listOf(
    Food(
        1,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
    Food(
        2,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
    Food(
        3,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
    Food(
        4,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
    Food(
        5,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
    Food(
        6,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
    Food(
        7,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
    Food(
        8,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
    Food(
        9,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
    Food(
        10,
        "Big cheese burger",
        "No 10 opp lekki phase 1 bridge in sangotedo estate",
        4.3,
        5.0,
        R.drawable.food_item_image,
        1,
    ),
)

@AndroidEntryPoint
class RestaurantFragment : Fragment() {
    private var _binding: FragmentRestaurantBinding? = null
    val binding get() = _binding!!
    private val restaurantViewModel: RestaurantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appBar.bringToFront()
        binding.restaurantScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            binding.appBar.setBackgroundColor(
                if (scrollY == 0) Color.TRANSPARENT else resources.getColor(
                    R.color.white
                )
            )
        }
        binding.foodRecyclerView.adapter = FoodRVAdapter(foodList)
    }
}