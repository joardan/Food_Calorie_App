package com.example.calorietrackerapp.AppLogic

import android.content.res.Configuration
import android.provider.ContactsContract.Data
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calorietrackerapp.Database.Meal
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.ScreenInfo.DailyCalorieIntakeScreen
import com.example.calorietrackerapp.UI.ScreenInfo.DeleteUpdateScreen
import com.example.calorietrackerapp.UI.ScreenInfo.FoodDetailScreen
import com.example.calorietrackerapp.UI.ScreenInfo.MainMenuScreen
import com.example.calorietrackerapp.UI.ScreenInfo.RetrieveFood
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CalorieTrackerApp(mealDAO: MealDAO) {
    val navController: NavHostController = rememberNavController()
    val viewModel: DataViewModel = viewModel()

    NavHost(navController = navController, startDestination = "MainMenuScreen") {
        composable(route = "MainMenuScreen") { MainMenuScreen(mealDAO = mealDAO,
            onNextButtonClickedLogMeal = {navController.navigate("FoodDetailScreen")},
            onNextButtonClickedShowMeals = {navController.navigate("DailyCalorieIntakeScreen")},
            onNextButtonClickedDeleteUpdateMeals = {navController.navigate("RetrieveFood")})}

        composable(route = "FoodDetailScreen") { FoodDetailScreen(mealDAO = mealDAO, viewModel = viewModel,onNextButtonClicked = {
            navController.navigate("MainMenuScreen")
            viewModel.updateMealName("")
            viewModel.updateMealType("")
            viewModel.updatePortionSize(0.0)
            viewModel.updateCalories(0.0)
            viewModel.updateProtein(0.0)
            viewModel.updateFat(0.0)
            viewModel.updateCarbs(0.0)
            viewModel.updateProgressIndicator(-1.0f)
        }) }
        composable(route = "DailyCalorieIntakeScreen") { DailyCalorieIntakeScreen(mealDAO = mealDAO, viewModel = viewModel, navController = navController, onNextButtonClicked = {
            navController.navigate("MainMenuScreen")
            viewModel.updateSelectedMeal(null)
        }) }

        composable(route = "RetrieveFood") { RetrieveFood(mealDAO = mealDAO, viewModel = viewModel, navController = navController, onNextButtonClicked = {
            navController.navigate("DeleteUpdateScreen")
        }) }

        composable(route = "DeleteUpdateScreen") { DeleteUpdateScreen(mealDAO = mealDAO, viewModel = viewModel, navController = navController, onNextButtonClicked = {
            navController.navigate("MainMenuScreen")
            viewModel.updateUpdateMeal(null)
        }) }
    }
}