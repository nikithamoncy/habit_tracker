package com.example.habittracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Habits::class], version = 1)
abstract class HabitDataBase : RoomDatabase(){
    abstract val habitDatabaseDao: HabitDatabaseDao
    companion object{
        @Volatile
        private var INSTANCE: HabitDataBase? = null
        fun getInstance(context: Context): HabitDataBase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HabitDataBase::class.java,
                        "habit_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}