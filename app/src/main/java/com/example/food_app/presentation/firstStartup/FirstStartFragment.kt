package com.example.food_app.presentation.firstStartup

import com.example.food_app.rvAdapters.CarouselRVAdapter
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.food_app.presentation.signUp.SignUpFragment
import com.example.food_app.R
import com.example.food_app.databinding.FragmentFirstStartBinding
import com.google.android.material.tabs.TabLayoutMediator
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

class FirstStartFragment : Fragment() {
    private var _binding: FragmentFirstStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstStartBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createViewPager(view)
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
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                onSelectedCarouselItemChange(position)
            }
        })

        binding.nextButton.setOnClickListener {
            if (viewPager.currentItem == CAROUSEL_ITEMS.size - 1
            ) {
                val ft: FragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction()
                val fragment = SignUpFragment()
                ft.replace(R.id.navHostFragment, fragment)
                ft.commit()
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

    private fun onSelectedCarouselItemChange(position: Int) {
//        var i = 0
//        for (id in carouselItems) {
//            val view = binding.root.findViewById<ImageView>(id)
//            view.setBackgroundResource(if (position == i) R.drawable.item_carousel_selected else R.drawable.item_carousel)
//            i++
//        }
    }
}