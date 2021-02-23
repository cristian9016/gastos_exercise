package com.example.misgastos.data

import android.content.Context
import android.content.SharedPreferences

object Prefs {

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences("mis_preferencias", Context.MODE_PRIVATE)
    }

    var isLogged: Boolean
        set(value) = preferences.edit().putBoolean("is_logged", value).apply()
        get() = preferences.getBoolean("is_logged", false)

    var gastos: String
        set(value) = preferences.edit().putString("gastos", value).apply()
        get() = preferences.getString("gastos", "") ?: ""
}
