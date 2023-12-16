package com.recon.dailyroutine.AdditionalClasses

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    // Метод для сохранения значения в SharedPreferences
    fun saveData(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    // Метод для получения значения из SharedPreferences
    fun loadData(key: String, defaultValue: String): String {
        return prefs.getString(key, defaultValue).toString()
    }
}
