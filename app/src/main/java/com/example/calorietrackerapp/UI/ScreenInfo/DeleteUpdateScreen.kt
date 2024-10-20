package com.example.calorietrackerapp.UI.ScreenInfo

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calorietrackerapp.AppLogic.DataViewModel
import com.example.calorietrackerapp.AppLogic.deleteImageFromStorage
import com.example.calorietrackerapp.Database.Meal
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.rectangularButton

@Composable
fun DeleteUpdateScreen(mealDAO: MealDAO, viewModel: DataViewModel, navController: NavController, onNextButtonClicked: () -> Unit) {
    val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    val uiState = viewModel.uiState.collectAsState()
    var meal = uiState.value.updateMeal
    val context = LocalContext.current


    // Set state variables based on the current meal details

    var foodName by remember { mutableStateOf(meal?.foodName ?: "") }
    //using ternary operator from typescript to fallback on default value in null
    var mealType by remember { mutableStateOf(meal?.mealType ?: "") }
    var portionSize by remember { mutableDoubleStateOf(meal?.portion ?: 0.0) }
    var calories by remember { mutableDoubleStateOf(meal?.calories ?: 0.0) }
    var protein by remember { mutableDoubleStateOf(meal?.protein ?: 0.0) }
    var fat by remember { mutableDoubleStateOf(meal?.fats ?: 0.0) }
    var carbs by remember { mutableDoubleStateOf(meal?.carbohydrates ?: 0.0) }

    Surface (
        modifier = Modifier
            .fillMaxSize(1f),
        color = Color(0xFF101111)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Food Database",
                fontSize = 25.sp,
                fontFamily = FontFamily.Serif,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(getHeaderPadding(portrait))
            )

            TextField(
                value = foodName,
                onValueChange = { foodName = it },
                label = { Text(text = "Food Name") },
                modifier = Modifier.padding(8.dp)
            )

            if (portrait) {
                TextField(
                    value = mealType,
                    onValueChange = { mealType = it },
                    label = { Text(text = "Meal Type") },
                    modifier = Modifier.padding(8.dp)
                )

                TextField(
                    value = portionSize.toString(),
                    onValueChange = { portionSize = it.toDoubleOrNull() ?: meal!!.portion },
                    label = { Text(text = "Portion Size") },
                    modifier = Modifier.padding(8.dp)
                )

                TextField(
                    value = calories.toString(),
                    onValueChange = { calories = it.toDoubleOrNull() ?: meal!!.calories },
                    label = { Text(text = "Calories") },
                    modifier = Modifier.padding(8.dp)
                )

                TextField(
                    value = protein.toString(),
                    onValueChange = { protein = it.toDoubleOrNull() ?: meal!!.protein },
                    label = { Text(text = "Protein (g)") },
                    modifier = Modifier.padding(8.dp)
                )

                TextField(
                    value = fat.toString(),
                    onValueChange = { fat = it.toDoubleOrNull() ?: meal!!.fats },
                    label = { Text(text = "Fat (g)") },
                    modifier = Modifier.padding(8.dp)
                )

                TextField(
                    value = carbs.toString(),
                    onValueChange = { carbs = it.toDoubleOrNull() ?: meal!!.carbohydrates },
                    label = { Text(text = "Carbs (g)") },
                    modifier = Modifier.padding(8.dp)
                )
            }
            else {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    TextField(
                        value = mealType,
                        onValueChange = { mealType = it },
                        label = { Text(text = "Meal Type") },
                        modifier = Modifier.padding(8.dp)
                    )

                    TextField(
                        value = portionSize.toString(),
                        onValueChange = { portionSize = it.toDoubleOrNull() ?: meal!!.portion },
                        label = { Text(text = "Portion Size") },
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    TextField(
                        value = calories.toString(),
                        onValueChange = { calories = it.toDoubleOrNull() ?: meal!!.calories },
                        label = { Text(text = "Calories") },
                        modifier = Modifier.padding(8.dp)
                    )

                    TextField(
                        value = protein.toString(),
                        onValueChange = { protein = it.toDoubleOrNull() ?: meal!!.protein },
                        label = { Text(text = "Protein (g)") },
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    TextField(
                        value = fat.toString(),
                        onValueChange = { fat = it.toDoubleOrNull() ?: meal!!.fats },
                        label = { Text(text = "Fat (g)") },
                        modifier = Modifier.padding(8.dp)
                    )

                    TextField(
                        value = carbs.toString(),
                        onValueChange = { carbs = it.toDoubleOrNull() ?: meal!!.carbohydrates },
                        label = { Text(text = "Carbs (g)") },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        val updatedMeal = updateMeal(
                            mealDAO = mealDAO, meal = meal!!, foodName = foodName,
                            portionSize = portionSize, calories = calories, protein = protein,
                            carbs = carbs, fat = fat, mealType = mealType, hasPhoto = null
                        )
                        mealDAO.updateMeal(updatedMeal)
                        meal = updatedMeal
                        viewModel.updateUpdateMeal(meal)
                    },
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Update",
                        fontFamily = FontFamily.Serif,
                        color = Color.Black
                    )
                }

                Button(
                    onClick = {
                        mealDAO.deleteMeal(meal!!)

                        meal?.let { pMeal ->
                            deleteImageFromStorage(pMeal.foodName, context)
                        }

                        meal = null
                        viewModel.updateUpdateMeal(null)
                        navController.navigate("RetrieveFood")
                    },
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Delete",
                        fontFamily = FontFamily.Serif,
                        color = Color.Black
                    )
                }

                Button(
                    onClick = onNextButtonClicked,
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Back",
                        fontFamily = FontFamily.Serif,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

fun updateMeal(mealDAO: MealDAO, meal: Meal, foodName: String, mealType: String, portionSize: Double, calories: Double, protein: Double, fat: Double, carbs: Double, hasPhoto: Boolean?): Meal {
    val updatedMeal = meal.copy(
        foodName = if (foodName.isNotEmpty()) foodName else meal.foodName,
        mealType = if (mealType.isNotEmpty()) mealType else meal.mealType,
        portion = if (portionSize != 0.0) portionSize else meal.portion,
        calories = if (calories != 0.0) calories else meal.calories,
        protein = if (protein != 0.0) protein else meal.protein,
        fats = if (fat != 0.0) fat else meal.fats,
        carbohydrates = if (carbs != 0.0) carbs else meal.carbohydrates,
        hasPhoto = hasPhoto ?: meal.hasPhoto
    )
    return updatedMeal
}

fun getHeaderPadding(portrait: Boolean) : Dp {
    if (portrait) {
        return 40.dp
    }
    return 5.dp
}