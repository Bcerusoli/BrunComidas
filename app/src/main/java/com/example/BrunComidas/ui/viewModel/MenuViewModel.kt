package com.example.BrunComidas.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.BrunComidas.model.login.Menus
import com.example.BrunComidas.model.login.Producto
import com.example.BrunComidas.repositories.RestauranteRepository

class MenuViewModel : ViewModel() {
    val menu: MutableLiveData<Menus> = MutableLiveData()


    fun getRestaurantMenu(restaurantId: Long) {
        RestauranteRepository.getRestaurantMenu(restaurantId, { menu ->
            this.menu.value = menu
        }, { error ->
            // Handle error
        })
    }



}