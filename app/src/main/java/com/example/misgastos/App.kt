package com.example.misgastos

import android.app.Application
import com.example.misgastos.data.Prefs

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Prefs.init(this)
    }
}
