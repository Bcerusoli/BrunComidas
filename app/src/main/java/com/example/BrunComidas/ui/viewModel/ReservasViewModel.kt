package com.example.BrunComidas.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.BrunComidas.api.APIrestaurantes
import com.example.BrunComidas.model.login.Reservation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ReservasViewModel : ViewModel() {
    private val _reservas = MutableLiveData<List<Reservation>>()
    val reservas: LiveData<List<Reservation>> get() = _reservas

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://restaurantes.jmacboy.com/api/") // Reemplaza esto con la URL base de tu API
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(APIrestaurantes::class.java)

    fun getReservations() {
        api.getReservations().enqueue(object : Callback<List<Reservation>> {
            override fun onResponse(call: Call<List<Reservation>>, response: Response<List<Reservation>>) {
                if (response.isSuccessful) {
                    _reservas.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Reservation>>, t: Throwable) {
                // Maneja el error
            }
        })
    }
}