package com.example.food_app

import CarouselRVAdapter
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.food_app.databinding.FragmentFirstStartBinding
import java.lang.Math.abs

data class CarouselItem(
    val text: String,
    val image: Int
)

class FirstStartFragment : Fragment() {
    private var _binding: FragmentFirstStartBinding? = null
    private val binding get() = _binding!!

    private lateinit var carouselNavigationButtonsIds: List<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstStartBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val imageView = view.findViewById<ImageView>(R.id.image1)
        imageView.setBackgroundResource(R.drawable.item_carousel_selected)

        val carouselImages = listOf(
            R.drawable.map,
            R.drawable.food,
            R.drawable.delivery
        )

        viewPager.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER // Remove the scroll effect
        }

        viewPager.adapter = CarouselRVAdapter(carouselImages)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                onSelectedCarouselItemChange(position)
            }
        })

        binding.nextButton.setOnClickListener {
            viewPager.currentItem = 2
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((40 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        viewPager.setPageTransformer(compositePageTransformer)
    }

    private fun onSelectedCarouselItemChange(position: Int) {
        var i = 0
        for (id in carouselItems) {
            val view = binding.root.findViewById<ImageView>(id)
            view.setBackgroundResource(if (position == i) R.drawable.item_carousel_selected else R.drawable.item_carousel)
            i++
        }
    }
}