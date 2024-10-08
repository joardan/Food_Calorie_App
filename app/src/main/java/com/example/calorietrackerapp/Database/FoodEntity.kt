package com.example.calorietrackerapp.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meals")
data class Meal(
    @PrimaryKey @ColumnInfo(name = "FoodName") val foodName: String = "",
    @ColumnInfo(name = "PortionSize") val portion: Double = 0.0,
    @ColumnInfo(name = "Calories") val calories: Double = 0.0,
    @ColumnInfo(name = "Protein") val protein: Double = 0.0,
    @ColumnInfo(name = "Carbohydrates") val carbohydrates: Double = 0.0,
    @ColumnInfo(name = "Fats") val fats: Double = 0.0,
    @ColumnInfo(name = "MealType") val mealType: String = "",
    @ColumnInfo(name = "hasPhoto") val hasPhoto: Boolean = false
)