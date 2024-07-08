package com.example.BrunComidas.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.BrunComidas.model.login.Restaurant
import com.example.BrunComidas.repositories.RestauranteRepository

class RestaurantDetailViewModel : ViewModel() {
    private val _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> get() = _restaurant

    fun getRestaurantDetails(restaurantId: Long) { // Changed parameter type to Long
        RestauranteRepository.getRestaurantDetails(restaurantId,
            success = { restaurant ->
                _restaurant.value = restaurant
            },
            failure = { error ->
                // Aquí puedes manejar el error
            })
    }
}