package com.example.food_app.rvAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app.R
import com.example.food_app.data.food.Food

class FoodRVAdapter(val foodList: List<Food>) : RecyclerView.Adapter<FoodRVAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodImageView = view.findViewById<ImageView>(R.id.foodItemImageView)
        val foodNameTextView = view.findViewById<TextView>(R.id.foodItemNameTextView)
        val foodDescriptionTextView = view.findViewById<TextView>(R.id.foodItemDescriptionTextView)
        val priceTextView = view.findViewById<TextView>(R.id.foodPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foodList[position]
        holder.foodNameTextView.text = item.name
        holder.foodDescriptionTextView.text = item.description
        holder.priceTextView.text = "${String.format("%.0f", item.price)}$"
        holder.foodImageView.setImageResource(item.image)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}