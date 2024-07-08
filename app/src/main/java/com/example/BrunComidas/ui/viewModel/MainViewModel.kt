package com.example.BrunComidas.ui.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.BrunComidas.repositories.PreferencesRepository
import com.example.BrunComidas.repositories.UserRepository
import com.example.BrunComidas.ui.main.SessionManager

class MainViewModel : ViewModel() {

    private val _errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>("")

    }

    val errorMessage: LiveData<String> get() = _errorMessage

    // Añade la variable isLoginAttempted
    var isLoginAttempted = false


    fun login(email: String, password: String, context: Context) {
        // Establece isLoginAttempted en verdadero cuando se intenta iniciar sesión
        isLoginAttempted = true

        UserRepository.doLogin(email,
            password,
            success = {
                if(it == null) {
                    _errorMessage.value = "Usuario o contraseña incorrectos"
                    return@doLogin
                }
                _errorMessage.value = ""
                Log.d("MainViewModel", "Token: ${it.access_token}")
                val token: String = it.access_token!!
                PreferencesRepository.saveToken(token, context)

                // Cuando el inicio de sesión es exitoso, establece isUserLoggedIn en true
                val sessionManager = SessionManager(context)
                sessionManager.isUserLoggedIn = true
            }, failure = {
                it.printStackTrace()
            })
    }
}