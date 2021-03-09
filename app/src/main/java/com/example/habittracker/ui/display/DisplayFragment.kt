package com.example.habittracker.ui.display

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.R
import com.example.habittracker.database.HabitDataBase
import com.example.habittracker.database.HabitRepository
import com.example.habittracker.databinding.FragmentDisplayBinding
import java.text.SimpleDateFormat
import java.util.*

class DisplayFragment : Fragment() {

    private lateinit var displayViewModel: DisplayViewModel
    private lateinit var binding: FragmentDisplayBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_display, container, false)
        val dao = HabitDataBase.getInstance(requireContext()).habitDatabaseDao
        val repository = HabitRepository(dao)
        val factory = DisplayViewModelFactory(repository)
        displayViewModel = ViewModelProvider(this,factory).get(DisplayViewModel::class.java)
        binding.displayViewModel = displayViewModel
        initRecyclerView()
        return binding.root
    }

    fun initRecyclerView() {
        binding.displayRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        displayHabits()
    }

    fun day(){
        val sCalendar = Calendar.getInstance()
        val dayLongName: String =
            sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())


        val input = "24-2-2020"
        val inFormat = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = inFormat.parse(input)
        val outFormat = SimpleDateFormat("EEEE")
        val goal: String = outFormat.format(date)
        Log.i("Date String", goal)

    }

    fun displayHabits() {
        displayViewModel.habits.observe(viewLifecycleOwner, Observer {

          binding.displayRecyclerView.adapter = DisplayRecyclerViewAdapter(it)

        })

        /*val dayHabits = displayViewModel.dayHabits("sunday")
        dayHabits.observe(viewLifecycleOwner, Observer {
            binding.displayRecyclerView.adapter = DisplayRecyclerViewAdapter(it)
        })*/
    }
}