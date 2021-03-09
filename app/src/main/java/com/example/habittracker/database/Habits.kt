package com.example.habittracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_table")
data class Habits (

    @PrimaryKey(autoGenerate = true)
    var habitId : Long = 0L,

    @ColumnInfo(name = "habit_name")
    var habitName : String = "",

    @ColumnInfo(name = "habit_description")
    var habitDescription : String = "",

    @ColumnInfo(name = "sunday")
    var sunday: Boolean = false,

    @ColumnInfo(name = "monday")
    var monday: Boolean = false,

    @ColumnInfo(name = "tuesday")
    var tuesday: Boolean = false,

    @ColumnInfo(name = "wednesday")
    var wednesday: Boolean = false,

    @ColumnInfo(name = "thursday")
    var thursday: Boolean = false,

    @ColumnInfo(name = "friday")
    var friday: Boolean = false,

    @ColumnInfo(name = "saturday")
    var saturday: Boolean = false,


    @ColumnInfo(name = "habit_time")
    var habitTime : String = "",

    @ColumnInfo(name = "habit_quote")
    var habitQuote : String = "",

    @ColumnInfo(name = "habit_reminder")
    var habitReminders: String = ""

)

