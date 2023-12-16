package com.recon.dailyroutine

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.recon.dailyroutine.AdditionalClasses.SharedPrefs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val savedData = SharedPrefs(this)


        if (savedData.loadData("init","false") != "true") {
            val i: Intent = Intent(this, SettingActivity::class.java)
            startActivity(i)
        }

        val testBtn: Button = findViewById(R.id.button)
        testBtn.setOnClickListener{
            val tst: Intent = Intent(this, WeatherActivity::class.java)
            startActivity(tst)
        }
    }
}