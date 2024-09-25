package com.example.calorietrackerapp.UI.ScreenInfo

enum class Screens(val title: String) {
    MainMenuScreen(title = "MainMenuScreen"),
    FoodDetailScreen(title = "FoodDetailScreen"), //reuse for breakfast lunch dinner etc
    MealScreen(title = "MealScreen"),
    DailyCalorieIntakeScreen(title = "DailyCalorieIntakeScreen")
}