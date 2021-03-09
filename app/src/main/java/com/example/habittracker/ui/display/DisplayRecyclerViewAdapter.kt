package com.example.habittracker.ui.display

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
import com.example.habittracker.database.Habits
import com.example.habittracker.databinding.HabitsListBinding

class DisplayRecyclerViewAdapter(private val habitsList: List<Habits>) : RecyclerView.Adapter<DisplayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: HabitsListBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.habits_list,
            parent, false
        )
        return DisplayViewHolder(binding)
    }

    override fun getItemCount(): Int = habitsList.size

    override fun onBindViewHolder(holder: DisplayViewHolder, position: Int) {
        holder.bind(habitsList[position])
    }

}

class DisplayViewHolder(val binding: HabitsListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(habits: Habits) {

        binding.nameTextView.text = habits.habitName
        binding.descriptionTextView.text = habits.habitDescription
        if (habits.sunday){
            binding.daySunday.text = "Sunday, "
        }
        if (habits.monday){
            binding.dayMonday.text = "Monday, "
        }
        if (habits.tuesday){
            binding.dayTuesday.text = "Tuesday"
        }
        if (habits.wednesday){
            binding.dayWednesday.text = "Wednesday, "
        }
        if (habits.thursday){
            binding.dayThursday.text = "Thursday, "
        }
        if (habits.friday){
            binding.dayFriday.text = "Friday, "
        }
        if (habits.saturday){
            binding.daySaturday.text = "Saturday, "
        }
        binding.timeTextView.text = habits.habitTime
        binding.reminderTextView.text = habits.habitReminders
        binding.quoteTextView.text = habits.habitQuote

    }

}