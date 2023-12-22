package com.recon.dailyroutine

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.recon.dailyroutine.AdditionalClasses.SharedPrefs


class SettingActivity : AppCompatActivity() {
    private lateinit var letsgoBtn : Button
    private lateinit var cityNameField : EditText
    private lateinit var interestField : EditText
    private lateinit var spinnerCurren : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        //UI elements
        letsgoBtn = findViewById(R.id.letsgoBtn)
        cityNameField = findViewById(R.id.cityNameField)
        interestField = findViewById(R.id.interestField)
        spinnerCurren = findViewById(R.id.currencySpinner)

        //Shared preferences
        val savedData = SharedPrefs(this)


        letsgoBtn.setOnClickListener{
            if (interestField.text.toString() != "" && cityNameField.text.toString() != ""){
                //Saving data for using inDaApp
                savedData.saveData("init", "true")
                savedData.saveData("currency", spinnerCurren.selectedItem.toString())
                savedData.saveData("city", cityNameField.text.toString())
                savedData.saveData("interests", interestField.text.toString())

                val i : Intent = Intent(this,MainActivity::class.java)
                startActivity(i)
            }
            else
                Toast.makeText(this, "You have empty fields", Toast.LENGTH_SHORT).show()
        }

    }
}