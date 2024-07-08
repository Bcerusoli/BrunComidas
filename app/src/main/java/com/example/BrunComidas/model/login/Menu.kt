package com.example.BrunComidas.model.login

typealias Menus = ArrayList<Menu>
data class Menu (
    val id: Long,
    val name: String,
    val restaurantID: Long,
    val plates: List<Plato>
)