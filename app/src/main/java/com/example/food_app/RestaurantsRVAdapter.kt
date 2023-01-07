package com.example.food_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app.data.restaurant.Restaurant

class RestaurantsRVAdapter(
    private val restaurantsList: List<Restaurant>,
    private val onItemClick: (item: Restaurant) -> Unit
) :
    RecyclerView.Adapter<RestaurantsRVAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restaurantImageView = view.findViewById<ImageView>(R.id.restaurantImageView)
        val restaurantNameTextView = view.findViewById<TextView>(R.id.restaurantNameTextView)
        val restaurantDeliveryTimeTextView =
            view.findViewById<TextView>(R.id.restaurantDeliveryTimeTextView)
        val restaurantRatingTextView = view.findViewById<TextView>(R.id.restaurantRatingTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = restaurantsList[position]
        with(holder) {
            restaurantImageView.setImageResource(item.image)
            restaurantNameTextView.text = item.name
            restaurantDeliveryTimeTextView.text = item.deliveryTime
            restaurantRatingTextView.text = item.rating.toString()
        }
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return restaurantsList.size
    }
}