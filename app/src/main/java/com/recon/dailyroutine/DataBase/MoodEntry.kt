package com.recon.dailyroutine.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_entries")
data class MoodEntry(
 @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "random_id")
//    val id:Long,
    val moodValue: Int,
    val date: String,
    val comment: String,
)
