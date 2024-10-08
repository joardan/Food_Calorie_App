package com.example.calorietrackerapp.UI.ScreenInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietrackerapp.Database.Meal
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.rectangularButton

@Composable
fun DeleteUpdateScreen(mealDAO: MealDAO, foodName: String, onNextButtonClicked: () -> Unit) {
    var height: Float = 125.0F
    var meal = mealDAO.getMealByFoodName(foodName)


    // Set state variables based on the current meal details

    var foodName by remember { mutableStateOf(meal?.foodName ?: "") }
    //using ternary operator from typescript to fallback on default value in null
    var mealType by remember { mutableStateOf(meal?.mealType ?: "") }
    var portionSize by remember { mutableStateOf(meal?.portion ?: 0.0) }
    var calories by remember { mutableStateOf(meal?.calories ?: 0.0) }
    var protein by remember { mutableStateOf(meal?.protein ?: 0.0) }
    var fat by remember { mutableStateOf(meal?.fats ?: 0.0) }
    var carbs by remember { mutableStateOf(meal?.carbohydrates ?: 0.0) }
    var servingSize by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Food Database", fontSize = 25.sp, modifier = Modifier.padding(25.dp))

        TextField(
            value = foodName,
            onValueChange = { foodName = it },
            placeholder = { Text(text = "Food Name") },
            modifier = Modifier.padding(8.dp)
        )

        TextField(
            value = mealType,
            onValueChange = { mealType = it },
            placeholder = { Text(text = "Meal Type") },
            modifier = Modifier.padding(8.dp)
        )

        TextField(
            value = portionSize.toString(),
            onValueChange = { portionSize = it.toDoubleOrNull() ?: meal!!.portion },
            placeholder = { Text(text = "Portion Size") },
            modifier = Modifier.padding(8.dp)
        )

        TextField(
            value = calories.toString(),
            onValueChange = { calories = it.toDoubleOrNull() ?: meal!!.calories },
            placeholder = { Text(text = "Calories") },
            modifier = Modifier.padding(8.dp)
        )

        TextField(
            value = protein.toString(),
            onValueChange = { protein = it.toDoubleOrNull() ?: meal!!.protein },
            placeholder = { Text(text = "Protein (g)") },
            modifier = Modifier.padding(8.dp)
        )

        TextField(
            value = fat.toString(),
            onValueChange = { fat = it.toDoubleOrNull() ?: meal!!.fats },
            placeholder = { Text(text = "Fat (g)") },
            modifier = Modifier.padding(8.dp)
        )

        TextField(
            value = carbs.toString(),
            onValueChange = { carbs = it.toDoubleOrNull() ?: meal!!.carbohydrates },
            placeholder = { Text(text = "Carbs (g)") },
            modifier = Modifier.padding(8.dp)
        )

        TextField(
            value = servingSize,
            onValueChange = { servingSize = it },
            placeholder = { Text(text = "Serving Size") },
            modifier = Modifier.padding(8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            rectangularButton(
                height = height,
                width = height,
                text = "UPDATE!",
                onClick = { var UM = updateMeal(mealDAO = mealDAO, meal = meal!!, foodName = foodName,
                    portionSize = portionSize, calories = calories, protein = protein,
                    carbs = carbs, fat = fat, mealType = mealType)
                    mealDAO.updateMeal(UM)
                     }
            )

            rectangularButton(
                height = height,
                width = height,
                text = "DELETE!",
                onClick = { mealDAO.deleteMeal(meal!!) }
            )

            rectangularButton(
                height = height,
                width = height,
                text = "GO BACK!",
                onClick = onNextButtonClicked
            )
        }
    }
}

fun updateMeal(mealDAO: MealDAO, meal: Meal, foodName: String, mealType: String, portionSize: Double, calories: Double, protein: Double, fat: Double, carbs: Double): Meal {
    val updatedMeal = meal.copy(
        foodName = if (foodName.isNotEmpty()) foodName else meal.foodName,
        mealType = if (mealType.isNotEmpty()) mealType else meal.mealType,
        portion = if (portionSize != 0.0) portionSize else meal.portion,
        calories = if (calories != 0.0) calories else meal.calories,
        protein = if (protein != 0.0) protein else meal.protein,
        fats = if (fat != 0.0) fat else meal.fats,
        carbohydrates = if (carbs != 0.0) carbs else meal.carbohydrates
    )
    return updatedMeal
}