package com.example.food_app.presentation.start

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.food_app.R
import com.example.food_app.databinding.FragmentStartBinding
import com.example.food_app.rvAdapters.CarouselRVAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.abs


data class CarouselItem(
    val text: String,
    val image: Int
)

val CAROUSEL_ITEMS = listOf(
    CarouselItem("Order from your favourite stores or vendors", R.drawable.map),
    CarouselItem("Choose from a wide range of  delicious meals", R.drawable.food),
    CarouselItem("Enjoy instant delivery and payment", R.drawable.delivery)
)

@AndroidEntryPoint
class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private val startViewModel: StartViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sharedPreferences =
            requireContext().getSharedPreferences(
                "food-app",
                Context.MODE_PRIVATE
            )

        val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

        if (isFirstLaunch) {
            createViewPager(view)
            return
        }

        if (startViewModel.isUserAuthorized()) {
            findNavController().navigate(R.id.homeFragment)
        } else {
            findNavController().navigate(R.id.authNavGraph)
        }
    }


    private fun createViewPager(view: View) {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }

        viewPager.adapter = CarouselRVAdapter(CAROUSEL_ITEMS)

        binding.nextButton.setOnClickListener {
            if (viewPager.currentItem == CAROUSEL_ITEMS.size - 1
            ) {
                with(sharedPreferences.edit()) {
                    putBoolean("isFirstLaunch", false)
                    apply()
                }

                findNavController().navigate(R.id.action_startFragment_to_authNavGraph)
            } else {
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager.setPageTransformer(compositePageTransformer)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        }.attach()
    }
}