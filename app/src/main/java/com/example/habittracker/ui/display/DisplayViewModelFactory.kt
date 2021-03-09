package com.example.habittracker.ui.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habittracker.database.HabitRepository
import com.example.habittracker.ui.add.AddHabitViewModel
import java.lang.IllegalArgumentException

class DisplayViewModelFactory(private val repository: HabitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayViewModel::class.java)){
            return DisplayViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}