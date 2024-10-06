package com.example.calorietrackerapp.UI.ScreenInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.*

@Composable
fun DailyCalorieIntakeScreen(mealDAO: MealDAO, onNextButtonClicked: () -> Unit) {
    var width = 200.0
    var height = 100.0
    
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Daily Calorie Intake Screen", fontSize = 25.sp)
        Text(text = "Total Calories", fontSize = 25.sp) //maybe do formatted string or some other way of doing info
        Text(text = "Fats", fontSize = 25.sp)
        Text(text = "Proteins", fontSize = 25.sp)
        Text(text = "Carbohydrates", fontSize = 25.sp)

        rectangularButton(height = height.toFloat(), width = width.toFloat(), text = "BACK!", onClick = onNextButtonClicked)
    }
}