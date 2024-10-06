package com.example.calorietrackerapp.UI.ScreenInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.*

@Composable
fun MainMenuScreen(mealDAO: MealDAO, onNextButtonClickedLogMeal: () -> Unit, onNextButtonClickedShowMeals: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "WELCOME To Calorie Tracking App", textAlign = TextAlign.Center)
        rectangularButton(height = 250.toFloat(), width = 250.toFloat(), text = "Log Meal", onClick = onNextButtonClickedLogMeal)
        rectangularButton(height = 250.toFloat(), width = 250.toFloat(), text = "Intake So Far",  onClick = onNextButtonClickedShowMeals)
    }
}