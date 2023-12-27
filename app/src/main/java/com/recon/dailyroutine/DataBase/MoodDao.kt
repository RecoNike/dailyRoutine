package com.recon.dailyroutine.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoodEntry(moodEntry: MoodEntry)

    @Query("SELECT * FROM mood_entries")
    suspend fun getAllMoodEntries(): List<MoodEntry>

    @Query("SELECT * FROM mood_entries WHERE date = :selectedDate")
    suspend fun getMoodEntriesForDate(selectedDate: String): List<MoodEntry>
}
