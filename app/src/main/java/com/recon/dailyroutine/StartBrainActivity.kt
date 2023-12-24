package com.recon.dailyroutine

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class StartBrainActivity : AppCompatActivity() {
    lateinit var number1:TextView
    lateinit var number2:TextView
    lateinit var checkButton: Button
    lateinit var ansField: EditText
    var num1:Int = 0
    var num2:Int = 0
    var correctAnswer = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_brain)
        number1 = findViewById(R.id.numberTv1)
        number2 = findViewById(R.id.numberTv2)
        checkButton = findViewById(R.id.checkBtn)
        ansField = findViewById(R.id.answerField)

        num1 = Random.nextInt(1,50)
        num2 =  Random.nextInt(1,50)
        number1.text = num1.toString()
        number2.text = num2.toString()

        correctAnswer = num1 + num2
        println("Numbers = $num1   $num2")
        println("Answer = $correctAnswer")
        checkButton.setOnClickListener{
            if (ansField.text.toString().toInt() == correctAnswer){
                println("${ansField.text.toString().toInt()}")
                Toast.makeText(this, "Correct!", LENGTH_SHORT).show()
                ansField.text.clear()
                NextMove()
            } else {
                Toast.makeText(this, "Wrong!", LENGTH_SHORT).show()
            }
        }
    }
    private fun NextMove(){
        num1 = Random.nextInt(1,50)
        num2 =  Random.nextInt(1,50)
        number1.text = num1.toString()
        number2.text = num2.toString()

        correctAnswer = num1 + num2
    }
}