package com.example.BrunComidas.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.BrunComidas.adapter.PhotoAdapter
import com.example.BrunComidas.databinding.ActivityRestaurantDetailBinding
import com.example.BrunComidas.ui.viewModel.RestaurantDetailViewModel

class RestaurantDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantDetailBinding
    private val viewModel: RestaurantDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val restaurantId = intent.getLongExtra("restaurantId", 0L)
        viewModel.getRestaurantDetails(restaurantId) // Removed .toInt()

        viewModel.restaurant.observe(this, { restaurant ->
            binding.restaurantName.text = restaurant.name
            binding.restaurantDescription.text = restaurant.description
            binding.restaurantCity.text = restaurant.city
            binding.restaurantAddress.text = restaurant.address

            // Configurar el RecyclerView con las fotos del restaurante
            val photoAdapter = PhotoAdapter(restaurant.photos)
            binding.photoRecyclerView.adapter = photoAdapter
        })

        // Encuentra el botón "Ver Menu" y establece un OnClickListener en él
        binding.viewMenuButton.setOnClickListener {
            // Inicia MenuActivity y pasa el ID del restaurante a ella
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("restaurant_id", restaurantId)
            startActivity(intent)
        }

        // Encuentra el botón "reservar" y establece un OnClickListener en él
        binding.reserveButton.setOnClickListener {
            // Inicia MainActivity para permitir al usuario iniciar sesión
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}