package com.example.habittracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.habittracker.R
import com.example.habittracker.adapter.CalendarRecyclerAdapter
import com.example.habittracker.databinding.FragmentHomeBinding
import com.example.habittracker.model.RecyclerCalendarConfiguration
import com.example.habittracker.utilities.CalendarUtils
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    var dayOfWeek: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.fab.setOnClickListener { view ->
            findNavController().navigate(R.id.action_nav_home_to_addHabitFragment)
        }


        val date = Date()
        date.time = System.currentTimeMillis()

        val startCal = Calendar.getInstance()

        val endCal = Calendar.getInstance()
        endCal.time = date
        endCal.add(Calendar.MONTH, 3)

        val configuration: RecyclerCalendarConfiguration =
            RecyclerCalendarConfiguration(
                calenderViewType = RecyclerCalendarConfiguration.CalenderViewType.HORIZONTAL,
                calendarLocale = Locale.getDefault(),
                includeMonthHeader = true
            )
        dayOfWeek =
            CalendarUtils.dateStringFromFormat(
                locale = configuration.calendarLocale,
                date = date,
                format = CalendarUtils.LONG_DATE_DAY
            ) ?: ""


        val calendarAdapterHorizontal: CalendarRecyclerAdapter =
            CalendarRecyclerAdapter(
                startDate = startCal.time,
                endDate = endCal.time,
                configuration = configuration,
                selectedDate = date,
                dateSelectListener = object :
                    CalendarRecyclerAdapter.OnDateSelected {
                    override fun onDateSelected(date: Date) {
                        dayOfWeek =
                            CalendarUtils.dateStringFromFormat(
                                locale = configuration.calendarLocale,
                                date = date,
                                format = CalendarUtils.LONG_DATE_DAY
                            )
                                ?: ""
                    }
                }
            )

        binding.calendarRecyclerView.adapter = calendarAdapterHorizontal
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.calendarRecyclerView)

        return binding.root
    }
}