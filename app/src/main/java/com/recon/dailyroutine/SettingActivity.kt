package com.recon.dailyroutine

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class SettingActivity : AppCompatActivity() {
    lateinit var letsgoBtn : Button
    lateinit var cityNameField : EditText
    lateinit var interestField : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        letsgoBtn = findViewById(R.id.letsgoBtn)
        cityNameField = findViewById(R.id.cityNameField)
        interestField = findViewById(R.id.interestField)


        letsgoBtn.setOnClickListener{
            if (interestField.text.toString() != "" && cityNameField.text.toString() != ""){
                val i : Intent = Intent(this,MainActivity::class.java)
                startActivity(i)
            } else Toast.makeText(this, "You have empty fields", Toast.LENGTH_SHORT).show()
        }

    }
}