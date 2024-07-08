package com.example.BrunComidas.api


import com.example.BrunComidas.model.login.Menus
import com.example.BrunComidas.model.login.Producto
import com.example.BrunComidas.model.login.Reservation
import com.example.BrunComidas.model.login.Restaurant
import com.example.BrunComidas.model.login.Restaurantes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIrestaurantes {

    @POST("restaurants/search")
    fun ListaRestaurantes(): Call<Restaurantes>

    @GET("restaurants/{id}")
    fun getRestauranteById(
        @Path("id") id: Long
    ): Call<Restaurant>

    @GET("restaurants/{id}/menu")
    fun getMenuById(
        @Path("id") id: Int
    ): Call<Menus>


    @GET("/api/reservations")
    fun getReservations(): Call<List<Reservation>>

    @GET("restaurants/{id}/menu")
    fun getRestaurantMenu(
        @Path("id") id: Long
    ): Call<Menus>
    @GET("/api/restaurants/{restaurantId}/menu")
    fun getMenuCategoryProducts(@Path("restaurantId") restaurantId: Long): Call<List<Producto>>

}