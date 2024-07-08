package com.example.BrunComidas.ui.main

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("MyApp", Context.MODE_PRIVATE)

    var isUserLoggedIn: Boolean
        get() = prefs.getBoolean("IS_LOGGED_IN", false)
        set(value) = prefs.edit().putBoolean("IS_LOGGED_IN", value).apply()

    fun clear() {
        prefs.edit().clear().apply()
    }
}