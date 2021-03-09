package com.example.habittracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDatabaseDao{

    @Insert
    suspend fun insert(habits: Habits)

    @Update
    suspend fun update(habits: Habits)

    @Delete
    suspend fun delete(habits: Habits)

    @Query("DELETE FROM habit_table")
    suspend fun clearAll()

    @Query("SELECT * FROM habit_table")
    fun getAllHabits() : LiveData<List<Habits>>

   /* @Query("SELECT * FROM habit_table WHERE sunday = 1")
    fun getDayHabits(day : String) : LiveData<List<Habits>>

*/
}
