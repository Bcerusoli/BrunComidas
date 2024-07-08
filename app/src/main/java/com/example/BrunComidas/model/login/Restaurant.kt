package com.example.BrunComidas.model.login

typealias Restaurantes = ArrayList<Restaurant>

data class Restaurant (
    val id: Long,
    val name: String,
    val address: String,
    val city: String,
    val description: String,
    val userID: Long,
    val logo: String,
    val photos: List<Photo>

)