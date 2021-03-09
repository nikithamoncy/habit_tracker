package com.example.habittracker.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habittracker.database.HabitRepository
import java.lang.IllegalArgumentException

class AddHabitViewModelFactory(private val repository: HabitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddHabitViewModel::class.java)){
            return AddHabitViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}