// DBSingleton.kt
package com.recon.dailyroutine.DBRoom

import android.content.Context
import com.recon.dailyroutine.DataBase.AppDatabase

object DBSingleton {
    lateinit var database: AppDatabase
        private set

    fun initialize(context: Context) {
        database = AppDatabase.getDatabase(context)
    }
}
