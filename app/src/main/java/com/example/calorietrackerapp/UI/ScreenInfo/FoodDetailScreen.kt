package com.example.calorietrackerapp.UI.ScreenInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietrackerapp.API.CalorieNinjaService
import com.example.calorietrackerapp.API.NutritionResponse
import com.example.calorietrackerapp.Database.Meal
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.*


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.round


@Composable
fun FoodDetailScreen(mealDAO: MealDAO, onNextButtonClicked: () -> Unit) {
    var width = 200.0
    var height = 100.0

    var MT: String = ""
    var FN: String = ""
    var PS: Double = 0.0

    var cals: Double = 0.0
    var prot: Double = 0.0
    var fats: Double = 0.0
    var carbs: Double = 0.0

    var portion: Double = 0.0

    var PORTIONSIZETAKEN by remember { mutableStateOf(portion) }



    var mealType by remember { mutableStateOf(MT) }
    var foodName by remember { mutableStateOf(FN) }
    var portionSize by remember { mutableStateOf(PS) }

    var calories by remember { mutableStateOf(cals) }
    var protein by remember { mutableStateOf(prot) }
    var fat by remember { mutableStateOf(fats) }
    var carb by remember { mutableStateOf(carbs) }

    val coroutineScope = rememberCoroutineScope()
    var nutritionData by remember { mutableStateOf<NutritionResponse?>(null) }

    val apiKey = "GjQeekGlBy8XhkVRRu46Gw==FzKWnNZpDuwpbodv"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.calorieninjas.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(CalorieNinjaService::class.java)

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = mealType,
            onValueChange = { mealType = it },
            label = { Text("Meal Type") },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            ),
            modifier = Modifier
                .size(width.dp, height.dp)
        )

        TextField(
            value = foodName,
            onValueChange = { foodName = it },
            label = { Text("Food Name") },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            ),
            modifier = Modifier
                .size(width.dp, height.dp)
        )

        TextField(
            value = portionSize.toString(),
            onValueChange = { portionSize = it.toDouble() },
            label = { Text("Portion Size (Float)") },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            ),
            modifier = Modifier
                .size(width.dp, height.dp)
        )

        Text(text = "cals: ${calories}")
        Text(text = "protein: ${protein}")
        Text(text = "fat: ${fat}")
        Text(text = "carb: ${carb}")


        rectangularButton(height = height.toFloat(), width = width.toFloat(), text = "Take Photo!", onClick = {})
        rectangularButton(height = height.toFloat(), width = width.toFloat(), text = "Upload Photo!", onClick = {})
        rectangularButton(height = height.toFloat(), width = width.toFloat(), text = "Retrieve Info!", onClick = {
            coroutineScope.launch {
                val response = service.getNutritionInfo(apiKey, foodName)
                if (response.isSuccessful) {

                    //100g standard serving size
                    PORTIONSIZETAKEN = portionSize / 100

                    nutritionData = response.body()
                    if (nutritionData != null && !nutritionData!!.items.isNullOrEmpty()) {

                        //100g standard serving size
                        PORTIONSIZETAKEN = portionSize / 100

                        val firstItem = nutritionData!!.items[0]
                        calories = round(firstItem.calories * PORTIONSIZETAKEN * 100) / 100
                        protein = round(firstItem.protein_g * PORTIONSIZETAKEN * 100) / 100
                        fat = round(firstItem.fat_total_g * PORTIONSIZETAKEN * 100) / 100
                        carb = round(firstItem.carbohydrates_total_g * PORTIONSIZETAKEN * 100) / 100

                        println(nutritionData)
                        println(calories)
                    } else {
                        // Log or handle the case where no items are returned
                        println("Response body is empty or items list is null.")
                    }
                } else {
                    println("Error fetching data")
                    println(nutritionData)
                }
            }
        })
        rectangularButton(
            height = height.toFloat(),
            width = width.toFloat(),
            text = "LOG IT!",
            onClick = {
                coroutineScope.launch {
                    // Check if the meal already exists in the database
                    val existingMeal = mealDAO.getMealByFoodName(foodName)
                    if (existingMeal == null) {
                        // Meal does not exist, insert the new meal
                        mealDAO.insertMeal(Meal(
                            foodName = foodName,
                            portion = PORTIONSIZETAKEN,
                            calories = calories,
                            protein = protein,
                            carbohydrates = carb,
                            fats = fat,
                            mealType = mealType
                        ))
                    } else {
                        // Meal already exists, you can choose to update it
                        mealDAO.updateMeal(Meal(
                            foodName = foodName,
                            portion = PORTIONSIZETAKEN,
                            calories = calories,
                            protein = protein,
                            carbohydrates = carb,
                            fats = fat,
                            mealType = mealType
                        ))
                    }
                    // Proceed to the next step
                    onNextButtonClicked()
                }
            }
        )

    }
}


