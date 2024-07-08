package com.example.BrunComidas.model.login
typealias Reservations = ArrayList<Reservation>
data class Reservation(
    val id: Long,
    val userId: Long,
    val restaurantId: Long,
    val date: String,
    val time: String,
    val people: Int,
    val status: String,
    val createdAt: String,
    val updatedAt: String
)