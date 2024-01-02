package com.recon.dailyroutine

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
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
           if(ansField.text.toString()!="") {
                if (ansField.text.toString().toInt() == correctAnswer) {
                    println("${ansField.text.toString().toInt()}")
                    Snackbar.make(checkButton, "Correct!", Snackbar.LENGTH_SHORT)
                        .show()
                    ansField.text.clear()
                    NextMove()
                } else {
                    Snackbar.make(checkButton, "Wrong!", Snackbar.LENGTH_SHORT)
                        .show()
                }
            } else {
                   Snackbar.make(checkButton, "You must write answer", Snackbar.LENGTH_SHORT)
                       .show()
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