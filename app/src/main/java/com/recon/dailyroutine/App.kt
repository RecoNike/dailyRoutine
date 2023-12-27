package com.recon.dailyroutine

import android.app.Application
import com.recon.dailyroutine.DBRoom.DBSingleton

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DBSingleton.initialize(this)
        println("DB is ready")
    }
}