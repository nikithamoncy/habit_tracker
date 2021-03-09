package com.example.habittracker.ui.add

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.example.habittracker.database.HabitDatabaseDao
import com.example.habittracker.database.HabitRepository
import com.example.habittracker.database.Habits
import kotlinx.coroutines.*

class AddHabitViewModel(private val repository: HabitRepository) : ViewModel(), Observable{
    val everyday = listOf<String>(
        "Mon", "Tue", "Wed", "Thu",
        "Fri", "Sat", "Sun"
    )

    val habits = repository.habits

    private val _timeOfDay = MutableLiveData<String>()
    val timeOfDay: LiveData<String> = _timeOfDay

    private val _dayOfWeek = MutableLiveData<MutableSet<String>>()
    val dayOfWeek: LiveData<MutableSet<String>> = _dayOfWeek

    private val _reminder = MutableLiveData<String>()
    val reminder: LiveData<String> = _reminder

    val name = MutableLiveData<String>()
    val desp = MutableLiveData<String>()
    val quote = MutableLiveData<String>()

    init {
        _timeOfDay.value = ""
        _dayOfWeek.value = mutableSetOf<String>()
        _reminder.value = ""
    }

    fun timefun(time: String) {
        if (_timeOfDay.value != time) {
            _timeOfDay.value = time
        }
    }

    fun dayAddFun(day: String) {
        _dayOfWeek.value?.let {
            if (day == "Everyday") {
                it.addAll(everyday)
            } else {
                it.add(day)
            }
        }
    }

    fun dayRemoveFun(day: String) {
        _dayOfWeek.value?.let {
            if (day == "Everyday") {
                it.removeAll(everyday)
            } else {
                it.remove(day)
            }
        }
    }

    fun reminderFun(rem: String) {
        _reminder.value = rem
    }

    fun doneButton(){
        val habits = Habits()
        habits.habitName = name.value!!
        habits.habitDescription = desp.value!!
        habits.habitQuote = quote.value!!
        habits.habitTime = _timeOfDay.value!!
        if (dayOfWeek.value!!.contains("Mon")){
            habits.monday = true
        }else{
            habits.monday = false
        }
        if (dayOfWeek.value!!.contains("Tue")){
            habits.tuesday = true
        }else{
            habits.tuesday = false
        }
        if (dayOfWeek.value!!.contains("Wed")){
            habits.wednesday = true
        }else{
            habits.wednesday = false
        }
        if (dayOfWeek.value!!.contains("Thu")){
            habits.thursday = true
        }else{
            habits.thursday = false
        }
        if (dayOfWeek.value!!.contains("Fri")){
            habits.friday = true
        }else{
            habits.friday = false
        }
        if (dayOfWeek.value!!.contains("Sat")){
            habits.saturday = true
        }else{
            habits.saturday = false
        }
        if (dayOfWeek.value!!.contains("Sun")){
            habits.sunday = true
        }else{
            habits.sunday = false
        }
        habits.habitReminders = _reminder.value!!
        insert(habits)
        name.value = ""
        desp.value = ""
        quote.value = ""
        _timeOfDay.value = ""
        _reminder.value = ""
        _dayOfWeek.value = mutableSetOf<String>()

    }

    fun insert(habits: Habits){
        viewModelScope.launch {
            repository.insert(habits)
        }
    }

    fun update(habits: Habits){
        viewModelScope.launch {
            repository.update(habits)
        }
    }

    fun delete(habits: Habits){
        viewModelScope.launch {
            repository.delete(habits)
        }
    }



    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}