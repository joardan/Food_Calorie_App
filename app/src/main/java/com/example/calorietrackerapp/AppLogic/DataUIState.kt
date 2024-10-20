package com.example.calorietrackerapp.AppLogic

import android.graphics.Bitmap
import com.example.calorietrackerapp.Database.Meal


data class DataUIState (
    var progressIndicator: Float = -1.0f,
    var mealType: String = "",
    var mealName: String = "",
    var portionSize: Double = 0.0,
    var getFoodName: String = "",
    var calories: Double = 0.0,
    var protein: Double = 0.0,
    var fat: Double = 0.0,
    var carbs: Double = 0.0,
    var bitmap: Bitmap? = null,
    var selectMeal: Meal? = null,
    var updateMeal: Meal? = null
)