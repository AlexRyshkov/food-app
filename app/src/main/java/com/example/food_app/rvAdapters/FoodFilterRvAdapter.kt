package com.example.food_app.rvAdapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app.R

data class FoodFilterItem(
    val name: String,
    val image: Int,
)

class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = spaceSize
            right = spaceSize
        }
    }
}

class FoodFilterRvAdapter(private val foodFilterList: List<FoodFilterItem>) :
    RecyclerView.Adapter<FoodFilterRvAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodFilterImageView = view.findViewById<ImageView>(R.id.foodFilterImageView)
        val foodFilterTextView = view.findViewById<TextView>(R.id.foodFilterTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.food_filter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foodFilterList[position]
        with(holder) {
            foodFilterImageView.setImageResource(item.image)
            foodFilterTextView.text = item.name
        }
    }

    override fun getItemCount(): Int {
        return foodFilterList.size
    }
}