package com.example.BrunComidas.repositories

import com.example.BrunComidas.api.APIrestaurantes
import com.example.BrunComidas.model.login.Menus
import com.example.BrunComidas.model.login.Producto
import com.example.BrunComidas.model.login.Restaurant
import com.example.BrunComidas.model.login.Restaurantes
import retrofit2.Call
import retrofit2.Callback

import retrofit2.Response

object RestauranteRepository {
    fun getRestaurantes(success: (Restaurantes?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APIrestaurantes =
            retrofit.create(APIrestaurantes::class.java)

        service.ListaRestaurantes().enqueue(object : Callback<Restaurantes> {
            override fun onResponse(res: Call<Restaurantes>, response: Response<Restaurantes>) {
                val postList = response.body()
                success(postList)
            }

            override fun onFailure(res: Call<Restaurantes>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getProductsFromMenuCategory(
        categoryId: Long,
        success: (List<Producto>?) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service: APIrestaurantes = retrofit.create(APIrestaurantes::class.java)

        service.getMenuCategoryProducts(categoryId).enqueue(object : Callback<List<Producto>> {
            override fun onResponse(
                call: Call<List<Producto>>,
                response: Response<List<Producto>>
            ) {
                if (response.isSuccessful) {
                    val productos = response.body()
                    println("Productos received: $productos") // Imprime los productos recibidos
                    success(productos)
                } else {
                    println("Error: ${response.errorBody()}") // Imprime el error si la respuesta no es exitosa
                }
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                println("Failure: $t") // Imprime el error si la llamada falla
                failure(t)
            }
        })
    }


        // New method to get details of a specific restaurant
        fun getRestaurantDetails(
            restaurantId: Long,
            success: (Restaurant?) -> Unit,
            failure: (Throwable) -> Unit
        ) {
            val retrofit = RetrofitRepository.getRetrofitInstance()
            val service: APIrestaurantes = retrofit.create(APIrestaurantes::class.java)

            service.getRestauranteById(restaurantId).enqueue(object : Callback<Restaurant> {
                override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                    val restaurant = response.body()
                    success(restaurant)
                }

                override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                    failure(t)
                }
            })
        }

        // New method to get the menu of a specific restaurant
        fun getRestaurantMenu(
            restaurantId: Long,
            success: (Menus?) -> Unit,
            failure: (Throwable) -> Unit
        ) {
            val retrofit = RetrofitRepository.getRetrofitInstance()
            val service: APIrestaurantes = retrofit.create(APIrestaurantes::class.java)

            service.getRestaurantMenu(restaurantId).enqueue(object : Callback<Menus> {
                override fun onResponse(call: Call<Menus>, response: Response<Menus>) {
                    if (response.isSuccessful) {
                        val menu = response.body()
                        println("Menu received: $menu") // Imprime el menú recibido
                        success(menu)
                    } else {
                        println("Error: ${response.errorBody()}") // Imprime el error si la respuesta no es exitosa
                    }
                }

                override fun onFailure(call: Call<Menus>, t: Throwable) {
                    println("Failure: $t") // Imprime el error si la llamada falla
                    failure(t)
                }
            })
        }
    }