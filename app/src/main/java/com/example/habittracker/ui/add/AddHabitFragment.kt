package com.example.habittracker.ui.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.habittracker.R
import com.example.habittracker.database.HabitDataBase
import com.example.habittracker.database.HabitRepository
import com.example.habittracker.databinding.AddHabitFragmentBinding

class AddHabitFragment : Fragment() {

    val everyday = listOf<String>(
        "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"
    )

    private lateinit var binding: AddHabitFragmentBinding
    private lateinit var viewModel: AddHabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_habit_fragment, container, false
        )
        val dao = HabitDataBase.getInstance(requireContext()).habitDatabaseDao
        val repository = HabitRepository(dao)
        val factory = AddHabitViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AddHabitViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            viewModel?.timeOfDay?.observe(viewLifecycleOwner, Observer {
                when (it) {
                    "6-9AM" -> btnCheck(earlyBtn)
                    "9-12PM" -> btnCheck(morningBtn)
                    "12-3AM" -> btnCheck(noonBtn)
                    "3-6PM" -> btnCheck(eveningBtn)
                    "6-9PM" -> btnCheck(nightBtn)
                    "9-12AM" -> btnCheck(lateBtn)
                    "anytime" -> btnCheck(anytimeBtn)
                    "exact" -> btnCheck(exactBtn)
                    else -> {
                        btnUncheck(earlyBtn)
                        btnUncheck(morningBtn)
                        btnUncheck(noonBtn)
                        btnUncheck(eveningBtn)
                        btnUncheck(nightBtn)
                        btnUncheck(lateBtn)
                        btnUncheck(anytimeBtn)
                        btnUncheck(exactBtn)
                    }
                }
            })

            viewModel?.dayOfWeek?.observe(viewLifecycleOwner, Observer {
                if (it.containsAll(everyday)) {
                    btnCheck(everydayBtn)
                    btnCheck(mondayBtn)
                    btnCheck(tuesdayBtn)
                    btnCheck(wednesdayBtn)
                    btnCheck(thursdayBtn)
                    btnCheck(fridayBtn)
                    btnCheck(saturdayBtn)
                    btnCheck(sundayBtn)
                } else {
                    btnUncheck(everydayBtn)
                    btnUncheck(mondayBtn)
                    btnUncheck(tuesdayBtn)
                    btnUncheck(wednesdayBtn)
                    btnUncheck(thursdayBtn)
                    btnUncheck(fridayBtn)
                    btnUncheck(saturdayBtn)
                    btnUncheck(sundayBtn)
                }
                if (it.contains("Mon")) {
                    btnCheck(mondayBtn)
                } else {
                    btnUncheck(mondayBtn)
                }
                if (it.contains("Tue")) {
                    btnCheck(tuesdayBtn)
                } else {
                    btnUncheck(tuesdayBtn)
                }
                if (it.contains("Wed")) {
                    btnCheck(wednesdayBtn)
                } else {
                    btnUncheck(wednesdayBtn)
                }
                if (it.contains("Thu")) {
                    btnCheck(thursdayBtn)
                } else {
                    btnUncheck(thursdayBtn)
                }
                if (it.contains("Fri")) {
                    btnCheck(fridayBtn)
                } else {
                    btnUncheck(fridayBtn)
                }
                if (it.contains("Sat")) {
                    btnCheck(saturdayBtn)
                } else {
                    btnUncheck(saturdayBtn)
                }
                if (it.contains("Sun")) {
                    btnCheck(sundayBtn)
                } else {
                    btnUncheck(sundayBtn)
                }
            })

            viewModel?.reminder?.observe(viewLifecycleOwner, Observer {
                if (it == "ON") {
                    reminderSwitch.isChecked = true
                    reminderSwitch.text = "ON"
                } else {
                    reminderSwitch.isChecked = false
                    reminderSwitch.text = "OFF"
                }
            })

            everydayBtn.setOnClickListener {
                if (everydayBtn.contentDescription == "unchecked") {
                    btnCheck(everydayBtn)
                    btnCheck(mondayBtn)
                    btnCheck(tuesdayBtn)
                    btnCheck(wednesdayBtn)
                    btnCheck(thursdayBtn)
                    btnCheck(fridayBtn)
                    btnCheck(saturdayBtn)
                    btnCheck(sundayBtn)
                    viewModel?.dayAddFun("Everyday")
                } else {
                    btnUncheck(everydayBtn)
                    btnUncheck(mondayBtn)
                    btnUncheck(tuesdayBtn)
                    btnUncheck(wednesdayBtn)
                    btnUncheck(thursdayBtn)
                    btnUncheck(fridayBtn)
                    btnUncheck(saturdayBtn)
                    btnUncheck(sundayBtn)
                    viewModel?.dayRemoveFun("Everyday")
                }
            }

            mondayBtn.setOnClickListener {
                if (mondayBtn.contentDescription == "unchecked") {
                    btnCheck(mondayBtn)
                    viewModel?.dayAddFun("Mon")

                } else {
                    btnUncheck(mondayBtn)
                    btnUncheck(everydayBtn)
                    viewModel?.dayRemoveFun("Mon")
                }
            }

            tuesdayBtn.setOnClickListener {
                if (tuesdayBtn.contentDescription == "unchecked") {
                    btnCheck(tuesdayBtn)
                    viewModel?.dayAddFun("Tue")

                } else {
                    btnUncheck(tuesdayBtn)
                    btnUncheck(everydayBtn)
                    viewModel?.dayRemoveFun("Tue")
                }
            }

            wednesdayBtn.setOnClickListener {
                if (wednesdayBtn.contentDescription == "unchecked") {
                    btnCheck(wednesdayBtn)
                    viewModel?.dayAddFun("Wed")

                } else {
                    btnUncheck(wednesdayBtn)
                    btnUncheck(everydayBtn)
                    viewModel?.dayRemoveFun("Wed")
                }
            }

            thursdayBtn.setOnClickListener {
                if (thursdayBtn.contentDescription == "unchecked") {
                    btnCheck(thursdayBtn)
                    viewModel?.dayAddFun("Thu")

                } else {
                    btnUncheck(thursdayBtn)
                    btnUncheck(everydayBtn)
                    viewModel?.dayRemoveFun("Thu")
                }
            }

            fridayBtn.setOnClickListener {
                if (fridayBtn.contentDescription == "unchecked") {
                    btnCheck(fridayBtn)
                    viewModel?.dayAddFun("Fri")

                } else {
                    btnUncheck(fridayBtn)
                    btnUncheck(everydayBtn)
                    viewModel?.dayRemoveFun("Fri")
                }
            }

            saturdayBtn.setOnClickListener {
                if (saturdayBtn.contentDescription == "unchecked") {
                    btnCheck(saturdayBtn)
                    viewModel?.dayAddFun("Sat")

                } else {
                    btnUncheck(saturdayBtn)
                    btnUncheck(everydayBtn)
                    viewModel?.dayRemoveFun("Sat")
                }
            }

            sundayBtn.setOnClickListener {
                if (sundayBtn.contentDescription == "unchecked") {
                    btnCheck(sundayBtn)
                    viewModel?.dayAddFun("Sun")

                } else {
                    btnUncheck(sundayBtn)
                    btnUncheck(everydayBtn)
                    viewModel?.dayRemoveFun("Sun")
                }
            }


            earlyBtn.setOnClickListener {
                if (earlyBtn.contentDescription == "unchecked") {
                    btnCheck(earlyBtn)
                    btnUncheck(morningBtn)
                    btnUncheck(noonBtn)
                    btnUncheck(eveningBtn)
                    btnUncheck(nightBtn)
                    btnUncheck(lateBtn)
                    btnUncheck(anytimeBtn)
                    btnUncheck(exactBtn)
                    viewModel?.timefun("6-9AM")
                } else {
                    btnUncheck(earlyBtn)
                }
            }

            morningBtn.setOnClickListener {
                if (morningBtn.contentDescription == "unchecked") {
                    btnCheck(morningBtn)
                    btnUncheck(earlyBtn)
                    btnUncheck(noonBtn)
                    btnUncheck(eveningBtn)
                    btnUncheck(nightBtn)
                    btnUncheck(lateBtn)
                    btnUncheck(anytimeBtn)
                    btnUncheck(exactBtn)
                    viewModel?.timefun("9-12PM")

                } else {
                    btnUncheck(morningBtn)
                }
            }

            noonBtn.setOnClickListener {
                if (noonBtn.contentDescription == "unchecked") {
                    btnCheck(noonBtn)
                    btnUncheck(earlyBtn)
                    btnUncheck(morningBtn)
                    btnUncheck(eveningBtn)
                    btnUncheck(nightBtn)
                    btnUncheck(lateBtn)
                    btnUncheck(anytimeBtn)
                    btnUncheck(exactBtn)
                    viewModel?.timefun("12-3AM")

                } else {
                    btnUncheck(noonBtn)
                }
            }

            eveningBtn.setOnClickListener {
                if (eveningBtn.contentDescription == "unchecked") {
                    btnCheck(eveningBtn)
                    btnUncheck(earlyBtn)
                    btnUncheck(morningBtn)
                    btnUncheck(noonBtn)
                    btnUncheck(nightBtn)
                    btnUncheck(lateBtn)
                    btnUncheck(anytimeBtn)
                    btnUncheck(exactBtn)
                    viewModel?.timefun("3-6PM")

                } else {
                    btnUncheck(eveningBtn)
                }
            }

            nightBtn.setOnClickListener {
                if (nightBtn.contentDescription == "unchecked") {
                    btnCheck(nightBtn)
                    btnUncheck(earlyBtn)
                    btnUncheck(morningBtn)
                    btnUncheck(noonBtn)
                    btnUncheck(eveningBtn)
                    btnUncheck(lateBtn)
                    btnUncheck(anytimeBtn)
                    btnUncheck(exactBtn)
                    viewModel?.timefun("6-9PM")

                } else {
                    btnUncheck(nightBtn)
                }
            }

            lateBtn.setOnClickListener {
                if (lateBtn.contentDescription == "unchecked") {
                    btnCheck(lateBtn)
                    btnUncheck(earlyBtn)
                    btnUncheck(morningBtn)
                    btnUncheck(noonBtn)
                    btnUncheck(eveningBtn)
                    btnUncheck(nightBtn)
                    btnUncheck(anytimeBtn)
                    btnUncheck(exactBtn)
                    viewModel?.timefun("9-12AM")

                } else {
                    btnUncheck(lateBtn)
                }
            }

            anytimeBtn.setOnClickListener {
                if (anytimeBtn.contentDescription == "unchecked") {
                    btnCheck(anytimeBtn)
                    btnUncheck(earlyBtn)
                    btnUncheck(morningBtn)
                    btnUncheck(noonBtn)
                    btnUncheck(eveningBtn)
                    btnUncheck(nightBtn)
                    btnUncheck(lateBtn)
                    btnUncheck(exactBtn)
                    viewModel?.timefun("anytime")
                } else {
                    btnUncheck(anytimeBtn)
                }
            }

            exactBtn.setOnClickListener {
                if (exactBtn.contentDescription == "unchecked") {
                    btnCheck(exactBtn)
                    btnUncheck(earlyBtn)
                    btnUncheck(morningBtn)
                    btnUncheck(noonBtn)
                    btnUncheck(eveningBtn)
                    btnUncheck(nightBtn)
                    btnUncheck(lateBtn)
                    btnUncheck(anytimeBtn)
                    viewModel?.timefun("exact")
                } else {
                    btnUncheck(exactBtn)
                }
            }


            reminderSwitch.setOnClickListener {
                if (reminderSwitch.isChecked) {
                    reminderSwitch.isChecked = true
                    reminderSwitch.text = "ON"
                    viewModel?.reminderFun("ON")
                } else {
                    reminderSwitch.isChecked = false
                    reminderSwitch.text = "OFF"
                    viewModel?.reminderFun("OFF")
                }
            }

        }
    }


    fun btnCheck(btn: Button) {
        btn.contentDescription = "checked"
        btn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
        btn.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    fun btnUncheck(btn: Button) {
        btn.contentDescription = "unchecked"
        btn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        btn.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
    }
}