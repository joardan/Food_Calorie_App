package com.example.calorietrackerapp.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meals")
data class Meal(
    @PrimaryKey @ColumnInfo(name = "FoodName") val foodName: String = "",
    @ColumnInfo(name = "PortionSize") val portion: Float = 0.0f,
    @ColumnInfo(name = "Calories") val calories: Float = 0.0f,
    @ColumnInfo(name = "Protein") val protein: Float = 0.0f,
    @ColumnInfo(name = "Carbohydrates") val carbohydrates: Float = 0.0f,
    @ColumnInfo(name = "Fats") val fats: Float = 0.0f,
    @ColumnInfo(name = "MealType") val mealType: String = ""
)