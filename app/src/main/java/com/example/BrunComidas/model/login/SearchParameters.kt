package com.example.BrunComidas.model.login

data class SearchParameters(
    val selectedDate: String,
    val startTime: String,
    val endTime: String,
    val city: String
)