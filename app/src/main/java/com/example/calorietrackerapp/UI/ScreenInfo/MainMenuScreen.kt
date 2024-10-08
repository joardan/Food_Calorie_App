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
fun MainMenuScreen(mealDAO: MealDAO, onNextButtonClickedLogMeal: () -> Unit,
                   onNextButtonClickedShowMeals: () -> Unit,
                   onNextButtonClickedDeleteUpdateMeals: () -> Unit) {

    var size: Float = 175.0F

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "WELCOME To Calorie Tracking App", textAlign = TextAlign.Center)
        rectangularButton(height = size, width = size, text = "Log Meal", onClick = onNextButtonClickedLogMeal)
        rectangularButton(height = size, width = size, text = "Intake So Far",  onClick = onNextButtonClickedShowMeals)
        rectangularButton(height = size, width = size, text = "Delete/Update",  onClick = onNextButtonClickedDeleteUpdateMeals)

    }
}