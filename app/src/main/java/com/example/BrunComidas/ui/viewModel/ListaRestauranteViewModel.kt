package com.example.BrunComidas.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.BrunComidas.model.login.Restaurantes
import com.example.BrunComidas.repositories.RestauranteRepository

class ListaRestauranteViewModel: ViewModel(){

    private val _restaurantes : MutableLiveData<Restaurantes> by lazy {
        MutableLiveData<Restaurantes>(Restaurantes())
    }

    val restaurantes: LiveData<Restaurantes> = _restaurantes

    fun fetchListaRestaurantes() {
        RestauranteRepository.getRestaurantes(
            success = { restaurantes ->
                restaurantes?.let {
                    _restaurantes.postValue(ArrayList(it))
                }
            },
            failure = {
                it.printStackTrace()
            })
    }


}