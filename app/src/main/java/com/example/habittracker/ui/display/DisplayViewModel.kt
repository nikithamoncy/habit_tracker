package com.example.habittracker.ui.display

import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.database.HabitRepository
import com.example.habittracker.database.Habits
import kotlinx.coroutines.launch

class DisplayViewModel(private val repository: HabitRepository) : ViewModel(), Observable {

    val habits = repository.habits
    /*fun dayHabits(day : String) : LiveData<List<Habits>>{
        return repository.getDayHabits(day)
    }*/

    fun clearAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }



}