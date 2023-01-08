package com.example.food_app.rvAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.food_app.R
import com.example.food_app.presentation.firstStartup.CarouselItem

class CarouselRVAdapter(private val carouselDataList: List<CarouselItem>) :
    RecyclerView.Adapter<CarouselRVAdapter.CarouselItemViewHolder>() {

    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
        return CarouselItemViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        val item = carouselDataList[position]
        val itemImageView = holder.itemView.findViewById<ImageView>(R.id.itemImageView)
        val itemTextView = holder.itemView.findViewById<TextView>(R.id.itemTextView)
        itemImageView.setImageResource(item.image)
        itemTextView.text = item.text
    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }
}