package com.example.calorietrackerapp.Database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var INSTANCE: MealDatabase? = null

    fun getDatabase(context: Context): MealDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MealDatabase::class.java,
                "MealDatabase"
            ).allowMainThreadQueries().build()
            INSTANCE = instance
            instance
        }
    }
}