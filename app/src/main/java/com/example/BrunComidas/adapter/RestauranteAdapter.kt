package com.example.BrunComidas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.BrunComidas.databinding.ItemRestauranteBinding
import com.example.BrunComidas.model.login.Restaurant
import com.example.BrunComidas.model.login.Restaurantes

class RestauranteAdapter(private val restaurantList : Restaurantes,
                         private val listener: OnRestaurantClickListener):
    RecyclerView.Adapter<RestauranteAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestauranteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RestaurantViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.bind(restaurant, listener)
    }

    override fun getItemCount(): Int {
        return restaurantList.size

    }
    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurant: Restaurant, listener: OnRestaurantClickListener) {
            val binding = ItemRestauranteBinding.bind(itemView)
            binding.apply {
                reNombreTxt.text = restaurant.name
                reDirecionTxt.text = restaurant.address
                Glide.with(itemView).load(restaurant.logo).into(imgRe)
                root.setOnClickListener {
                    listener.onRestaurantClick(restaurant)

                }
            }

        }

    }


    interface OnRestaurantClickListener {
        fun onRestaurantClick(restaurant: Restaurant)

    }
}

