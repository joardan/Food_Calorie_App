package com.example.calorietrackerapp.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MealDAO {
    @Insert
    fun insertMeal(meal: Meal)

    @Delete
    fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM Meals")
    fun getAllMeals(): List<Meal>

    @Update
    fun updateMeal(meal: Meal)


}