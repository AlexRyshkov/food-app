package com.example.food_app

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app.data.food.Food
import org.w3c.dom.Text

class FoodRVAdapter(val foodList: List<Food>) : RecyclerView.Adapter<FoodRVAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodImageView = view.findViewById<ImageView>(R.id.itemImageView)
        val foodNameTextView = view.findViewById<TextView>(R.id.foodItemNameTextView)
        val foodDescriptionTextView = view.findViewById<TextView>(R.id.foodItemDescriptionTextView)
        val priceTextView = view.findViewById<TextView>(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foodList[position]

    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}