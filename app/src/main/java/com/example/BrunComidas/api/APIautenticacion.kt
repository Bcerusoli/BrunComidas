package com.example.BrunComidas.api

import com.example.BrunComidas.model.login.LoginRequestDTO
import com.example.BrunComidas.model.login.LoginResponseDTO
import com.example.BrunComidas.model.login.Registro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIautenticacion {
    @POST("loginuser")
    fun login(@Body loginRequest: LoginRequestDTO): Call<LoginResponseDTO>

    //El token se tiene que mandar como Bearer + El string que se obtiene de PreferencesRepository.getToken()
    @POST("registeruser")
    fun register(@Body loginRequest: Registro): Call<Registro>

}