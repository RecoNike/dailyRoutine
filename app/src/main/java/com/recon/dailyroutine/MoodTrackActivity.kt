package com.recon.dailyroutine

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.recon.dailyroutine.DataBase.AppDatabase
import com.recon.dailyroutine.DataBase.MoodEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

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

    var pickedMood:Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_track)

        //DATABASE
        var moodEntry = MoodEntry(6, "01/01/2000","")
        val moodDao = AppDatabase.getDatabase(this@MoodTrackActivity).moodDao()
        var res : List<MoodEntry>

        val animView: LottieAnimationView = findViewById(R.id.animation_view)
        val test: TextView = findViewById(R.id.textView10)
        val seekMoodBar : SeekBar = findViewById(R.id.seekBar)
        val datePickButton : ImageButton = findViewById(R.id.dayPickBtn)
        val saveButton: Button = findViewById(R.id.saveBtn)
        val commentField : EditText = findViewById(R.id.commentInput)
        val dateTextView: TextView = findViewById(R.id.dateTv)
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        var dateString = simpleDateFormat.format(datePicker.selection)

        lifecycleScope.launch(Dispatchers.Main)
        {
            res = moodDao.getMoodEntriesForDate(dateString)
            if (!res.isEmpty()){
                seekMoodBar.progress = res[0].moodValue
                commentField.setText(res[0].comment)
            } else {
                seekMoodBar.progress = 5
                commentField.setText("")
                Snackbar.make(datePickButton, "No saved Data", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        dateTextView.text = dateString

        datePicker.addOnPositiveButtonClickListener {
            dateString = simpleDateFormat.format(datePicker.selection)
            dateTextView.text = dateString
            lifecycleScope.launch(Dispatchers.Main)
                {
                    res = moodDao.getMoodEntriesForDate(dateString)
                    if (!res.isEmpty()){
                        seekMoodBar.progress = res[0].moodValue
                        commentField.setText(res[0].comment)
                    } else {
                        seekMoodBar.progress = 5
                        commentField.setText("")
                        Snackbar.make(datePickButton, "No saved Data", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
        }

        datePickButton.setOnClickListener{
            datePicker.show(supportFragmentManager,"DayPicker")
            println(dateString)
        }

        saveButton.setOnClickListener{
            moodEntry = MoodEntry(pickedMood, dateString, commentField.text.toString())
                lifecycleScope.launch(Dispatchers.Main)
                {
                    moodDao.insertMoodEntry(moodEntry)
                }
//            seekMoodBar.progress = 5
//            commentField.setText("")
            Snackbar.make(saveButton, "Data Saved", Snackbar.LENGTH_SHORT)
                .show()
        }

//        lifecycleScope.launch(Dispatchers.Main) {
//            moodDao.insertMoodEntry(moodEntry)
//        }
//        lifecycleScope.launch(Dispatchers.Main) {
//            val res = moodDao.getMoodEntriesForDate("22.12.2023")
//            println(res)
//        }

        seekMoodBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress : Int, fromUser : Boolean){
                when(progress){
                    0 ->{
                            animView.setAnimationFromUrl(animation0)
                            animView.playAnimation()
                            pickedMood = progress
                    }
                    1 ->{
                        animView.setAnimationFromUrl(animation1)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                    2 ->{
                        animView.setAnimationFromUrl(animation2)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                    3 ->{
                        animView.setAnimationFromUrl(animation3)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                    4 ->{
                        animView.setAnimationFromUrl(animation4)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                    5 ->{
                        animView.setAnimationFromUrl(animation5)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                    6 ->{
                        animView.setAnimationFromUrl(animation6)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                    7 ->{
                        animView.setAnimationFromUrl(animation7)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                    8 ->{
                        animView.setAnimationFromUrl(animation8)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                    9 ->{
                        animView.setAnimationFromUrl(animation9)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                    10 ->{
                        animView.setAnimationFromUrl(animation10)
                        animView.playAnimation()
                        pickedMood = progress
                    }
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?){

            }
            override fun onStopTrackingTouch(seekBar: SeekBar){

            }
        })


    }
}

