package com.example.calorietrackerapp.AppLogic

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.ScreenInfo.DailyCalorieIntakeScreen
import com.example.calorietrackerapp.UI.ScreenInfo.DeleteUpdateScreen
import com.example.calorietrackerapp.UI.ScreenInfo.FoodDetailScreen
import com.example.calorietrackerapp.UI.ScreenInfo.MainMenuScreen

@Composable
fun CalorieTrackerApp(mealDAO: MealDAO) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "MainMenuScreen") {
        composable(route = "MainMenuScreen") { MainMenuScreen(mealDAO = mealDAO,
            onNextButtonClickedLogMeal = {navController.navigate("FoodDetailScreen")},
            onNextButtonClickedShowMeals = {navController.navigate("DailyCalorieIntakeScreen")},
            onNextButtonClickedDeleteUpdateMeals = {navController.navigate("DeleteUpdateScreen")})}

        composable(route = "FoodDetailScreen") { FoodDetailScreen(mealDAO = mealDAO,onNextButtonClicked = {
            navController.navigate("MainMenuScreen")
        }) }
        composable(route = "DailyCalorieIntakeScreen") { DailyCalorieIntakeScreen(mealDAO = mealDAO, onNextButtonClicked = {
            navController.navigate("MainMenuScreen")
        }) }

        composable(route = "DeleteUpdateScreen") { DeleteUpdateScreen(mealDAO = mealDAO, onNextButtonClicked = {
            navController.navigate("MainMenuScreen")
        }) }
    }
}