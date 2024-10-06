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

    @Query("SELECT SUM(Calories) FROM Meals")
    fun getTotalCalories(): Float?

    @Query("SELECT SUM(Protein) FROM Meals")
    fun getTotalProtein(): Float?

    @Query("SELECT SUM(Carbohydrates) FROM Meals")
    fun getTotalCarbohydrates(): Float?

    @Query("SELECT SUM(Fats) FROM Meals")
    fun getTotalFats(): Float?

//maybe need to consider suspend function id

}