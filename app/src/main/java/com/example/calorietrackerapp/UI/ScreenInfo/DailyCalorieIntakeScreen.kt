package com.example.calorietrackerapp.UI.ScreenInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.calorietrackerapp.Database.Meal
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.*

@Composable
fun DailyCalorieIntakeScreen(mealDAO: MealDAO, onNextButtonClicked: () -> Unit) {
    var mealList = mealDAO.getAllMeals()
    var totalCals = mealDAO.getTotalCalories()
    var totalProt = mealDAO.getTotalProtein()
    var totalFat = mealDAO.getTotalFats()
    var totalCarbs = mealDAO.getTotalCarbohydrates()

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
        Text(text = "Total Calories: ${totalCals}", fontSize = 25.sp) //maybe do formatted string or some other way of doing info
        Text(text = "Fats: ${totalFat}", fontSize = 25.sp)
        Text(text = "Proteins: ${totalProt}", fontSize = 25.sp)
        Text(text = "Carbohydrates: ${totalCarbs}", fontSize = 25.sp)

        rectangularButton(height = height.toFloat(), width = width.toFloat(), text = "BACK!", onClick = onNextButtonClicked)

        LazyColumn(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            items(mealList) { meal -> DisplayMeal(meal = meal) }
        }
    }
}

@Composable
fun DisplayMeal(meal: Meal) {
    var name = meal.foodName
    var calories = meal.calories
    var type = meal.mealType
    var protein = meal.protein
    var fats = meal.fats
    var carbs = meal.carbohydrates
    var portion = meal.portion

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(25.dp)) {
        Text(text = "Name: $name", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold, color = Color.Red)
        Text(text = "Type : $type", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
        Text(text = "Calories: $calories", fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Color.Green)
        Text(text = "Portion: $portion", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold, color = Color.Red)
        Text(text = "Protein : $protein", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
        Text(text = "Carbohydrates: $carbs", fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Color.Green)
        Text(text = "Fats: $fats", fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Color.Green)
    }
}
