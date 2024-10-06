package com.example.calorietrackerapp

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calorietrackerapp.AppLogic.CalorieTrackerApp
import com.example.calorietrackerapp.Database.DatabaseProvider
import com.example.calorietrackerapp.UI.ScreenInfo.DailyCalorieIntakeScreen
import com.example.calorietrackerapp.UI.ScreenInfo.FoodDetailScreen
import com.example.calorietrackerapp.UI.ScreenInfo.MainMenuScreen
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mealDB = DatabaseProvider.getDatabase(this)

            setContent {
                CalorieTrackerApp(mealDAO = mealDB.MealDAO())
            }
        }
    }
}

