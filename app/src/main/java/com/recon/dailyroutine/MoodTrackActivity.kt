package com.recon.dailyroutine

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class MoodTrackActivity : AppCompatActivity() {
    val animation0 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f92c/lottie.json"
    val animation1 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f622/lottie.json"
    val animation2 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f613/lottie.json"
    val animation3 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f62e_200d_1f4a8/lottie.json"
    val animation4 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f974/lottie.json"
    val animation5 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f610/lottie.json"
    val animation6 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f973/lottie.json"
    val animation7 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f60f/lottie.json"
    val animation8 = "https://fonts.gstatic.com/s/e/notoemoji/latest/263a_fe0f/lottie.json"
    val animation9 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f601/lottie.json"
    val animation10 = "https://fonts.gstatic.com/s/e/notoemoji/latest/1f603/lottie.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_track)
        val animView: LottieAnimationView = findViewById(R.id.animation_view)
        val test: TextView = findViewById(R.id.textView10)
        val seekMoodBar : SeekBar = findViewById(R.id.seekBar)

            test.setOnClickListener {
                animView.setAnimationFromUrl(animation1)
                animView.playAnimation()
        }

        seekMoodBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress : Int, fromUser : Boolean){
                when(progress){
                    0 ->{animView.setAnimationFromUrl(animation0)
                        animView.playAnimation()}
                    1 ->{animView.setAnimationFromUrl(animation1)
                        animView.playAnimation()}
                    2 ->{animView.setAnimationFromUrl(animation2)
                        animView.playAnimation()}
                    3 ->{animView.setAnimationFromUrl(animation3)
                        animView.playAnimation()}
                    4 ->{animView.setAnimationFromUrl(animation4)
                        animView.playAnimation()}
                    5 ->{animView.setAnimationFromUrl(animation5)
                        animView.playAnimation()}
                    6 ->{animView.setAnimationFromUrl(animation6)
                        animView.playAnimation()}
                    7 ->{animView.setAnimationFromUrl(animation7)
                        animView.playAnimation()}
                    8 ->{animView.setAnimationFromUrl(animation8)
                        animView.playAnimation()}
                    9 ->{animView.setAnimationFromUrl(animation9)
                        animView.playAnimation()}
                    10 ->{animView.setAnimationFromUrl(animation10)
                        animView.playAnimation()}
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?){

            }
            override fun onStopTrackingTouch(seekBar: SeekBar){

            }
        })


    }
}

