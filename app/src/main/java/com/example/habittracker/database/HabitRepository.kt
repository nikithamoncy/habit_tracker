package com.example.habittracker.database

import androidx.lifecycle.LiveData
import java.time.DayOfWeek

class HabitRepository(private val dao: HabitDatabaseDao) {

    val habits = dao.getAllHabits()
/*
    fun getDayHabits(day : String) : LiveData<List<Habits>>{
        return dao.getDayHabits(day)
    }*/
    
    suspend fun insert(habits: Habits) {
        dao.insert(habits)
    }

    suspend fun update(habits: Habits) {
        dao.update(habits)
    }

    suspend fun delete(habits: Habits) {
        dao.delete(habits)
    }

    suspend fun deleteAll() {
        dao.clearAll()
    }

}