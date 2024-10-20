package com.example.calorietrackerapp.UI.ScreenInfo

import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietrackerapp.API.CalorieNinjaService
import com.example.calorietrackerapp.API.NutritionResponse
import com.example.calorietrackerapp.AppLogic.DataViewModel
import com.example.calorietrackerapp.AppLogic.uploadBitmapToCloudStorage
import com.example.calorietrackerapp.Database.Meal
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.*

import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.round


@Composable
fun FoodDetailScreen(mealDAO: MealDAO, viewModel: DataViewModel, onNextButtonClicked: () -> Unit) {
    val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    val uiState = viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var mealType by remember { mutableStateOf(uiState.value.mealType) }
    var foodName by remember { mutableStateOf(uiState.value.mealName) }
    var portionSize by remember { mutableDoubleStateOf(uiState.value.portionSize) }

    var calories by remember { mutableDoubleStateOf(uiState.value.calories) }
    var protein by remember { mutableDoubleStateOf(uiState.value.protein) }
    var fat by remember { mutableDoubleStateOf(uiState.value.fat) }
    var carb by remember { mutableDoubleStateOf(uiState.value.carbs) }

    val coroutineScope = rememberCoroutineScope()
    var nutritionData by remember { mutableStateOf<NutritionResponse?>(null) }

    val apiKey = "GjQeekGlBy8XhkVRRu46Gw==FzKWnNZpDuwpbodv"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.calorieninjas.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(CalorieNinjaService::class.java)

    // PHOTO INTEGRATION
    var thumbnailImage by remember { mutableStateOf(uiState.value.bitmap) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            thumbnailImage = bitmap
            viewModel.updateBitmap(bitmap)
        }
        else {
            viewModel.updateBitmap(null)
        }
    }

    Surface (
        modifier = Modifier
            .fillMaxSize(1f),
        color = Color(0xFF101111)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.value.progressIndicator < 0.0) {
                if (portrait) {
                    TextField(
                        value = mealType,
                        onValueChange = {
                            mealType = it
                            viewModel.updateMealType(mealType)
                        },
                        label = { Text("Meal Type") },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .padding(15.dp)
                    )

                    TextField(
                        value = foodName,
                        onValueChange = {
                            foodName = it
                            viewModel.updateMealName(foodName)
                        },
                        label = { Text("Food Name") },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .padding(15.dp)
                    )

                    TextField(
                        value = portionSize.toString(),
                        onValueChange = {
                            portionSize = it.toDouble()
                            viewModel.updatePortionSize(portionSize)
                        },
                        label = { Text("Portion Size (g) (Float)") },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .padding(15.dp)
                    )

                    FoodStatText(text = "cals: $calories")
                    FoodStatText(text = "protein: $protein")
                    FoodStatText(text = "fat: $fat")
                    FoodStatText(text = "carb: $carb")

                    MenuButton(text = "Take Photo") {
                        cameraLauncher.launch()
                    }

                    thumbnailImage?.let { bitmap: Bitmap ->
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Captured Image",
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(100.dp)
                        )
                    }

                    MenuButton(text = "Retrieve Info") {
                        coroutineScope.launch {
                            val response = service.getNutritionInfo(apiKey, foodName)
                            if (response.isSuccessful) {
                                nutritionData = response.body()
                                if (nutritionData != null && nutritionData!!.items.isNotEmpty()) {

                                    val firstItem = nutritionData!!.items[0]
                                    calories = round(firstItem.calories * portionSize / firstItem.serving_size_g * 100) / 100
                                    viewModel.updateCalories(calories)
                                    protein = round(firstItem.protein_g * portionSize / firstItem.serving_size_g * 100) / 100
                                    viewModel.updateProtein(protein)
                                    fat = round(firstItem.fat_total_g * portionSize / firstItem.serving_size_g * 100) / 100
                                    viewModel.updateFat(fat)
                                    carb = round(firstItem.carbohydrates_total_g * portionSize / firstItem.serving_size_g * 100) / 100
                                    viewModel.updateCarbs(carb)

                                    println(nutritionData)
                                    println(calories)
                                }
                                else {
                                    // Log or handle the case where no items are returned
                                    println("Response body is empty or items list is null.")
                                    Toast.makeText(context, "Failed to retrieve information for $foodName", Toast.LENGTH_SHORT).show()

                                    calories = 0.0
                                    viewModel.updateCalories(calories)
                                    protein = 0.0
                                    viewModel.updateProtein(protein)
                                    fat = 0.0
                                    viewModel.updateFat(fat)
                                    carb = 0.0
                                    viewModel.updateCarbs(carb)
                                }
                            }
                            else {
                                println("Error fetching data")
                                println(nutritionData)
                                Toast.makeText(context, "Failed to retrieve information for $foodName", Toast.LENGTH_SHORT).show()

                                calories = 0.0
                                viewModel.updateCalories(calories)
                                protein = 0.0
                                viewModel.updateProtein(protein)
                                fat = 0.0
                                viewModel.updateFat(fat)
                                carb = 0.0
                                viewModel.updateCarbs(carb)
                            }
                        }
                    }

                    MenuButton(text = "Log it") {
                        coroutineScope.launch {
                            // Check if the meal already exists in the database
                            val existingMeal = mealDAO.getMealByFoodName(foodName)
                            if (existingMeal == null) {
                                // Meal does not exist, insert the new meal
                                mealDAO.insertMeal(
                                    Meal(
                                        foodName = foodName,
                                        portion = portionSize,
                                        calories = calories,
                                        protein = protein,
                                        carbohydrates = carb,
                                        fats = fat,
                                        mealType = mealType,
                                        hasPhoto = (thumbnailImage != null)
                                    )
                                )
                            }
                            else {
                                // Meal already exists, you can choose to update it
                                mealDAO.updateMeal(
                                    Meal(
                                        foodName = foodName,
                                        portion = portionSize,
                                        calories = calories,
                                        protein = protein,
                                        carbohydrates = carb,
                                        fats = fat,
                                        mealType = mealType,
                                        hasPhoto = (thumbnailImage != null)
                                    )
                                )

                                Toast.makeText(context, "Meal already existed, updating instead.", Toast.LENGTH_SHORT).show()
                            }

                            thumbnailImage?.let {
                                if (foodName.isNotEmpty()) {
                                    uploadBitmapToCloudStorage(it, "$foodName.jpg", viewModel, context)
                                }
                            }

                            if (thumbnailImage == null)
                                onNextButtonClicked()
                        }
                    }

                    MenuButton(text = "Back") {
                        onNextButtonClicked()
                    }
                }
                else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                    ) {
                        TextField(
                            value = mealType,
                            onValueChange = {
                                mealType = it
                                viewModel.updateMealType(mealType)
                            },
                            label = { Text("Meal Type") },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .padding(15.dp)
                        )

                        TextField(
                            value = foodName,
                            onValueChange = {
                                foodName = it
                                viewModel.updateMealName(foodName)
                            },
                            label = { Text("Food Name") },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .padding(15.dp)
                        )

                        TextField(
                            value = portionSize.toString(),
                            onValueChange = {
                                portionSize = it.toDouble()
                                viewModel.updatePortionSize(portionSize)
                            },
                            label = { Text("Portion Size (g) (Float)") },
                            textStyle = LocalTextStyle.current.copy(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .padding(15.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                    ) {
                        FoodStatText(text = "cals: $calories")
                        FoodStatText(text = "protein: $protein")
                        FoodStatText(text = "fat: $fat")
                        FoodStatText(text = "carb: $carb")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                    ) {
                        MenuButton(text = "Take Photo") {
                            cameraLauncher.launch()
                        }

                        MenuButton(text = "Retrieve Info") {
                            coroutineScope.launch {
                                val response = service.getNutritionInfo(apiKey, foodName)
                                if (response.isSuccessful) {
                                    nutritionData = response.body()
                                    if (nutritionData != null && nutritionData!!.items.isNotEmpty()) {
                                        val firstItem = nutritionData!!.items[0]
                                        calories = round(firstItem.calories * portionSize / firstItem.serving_size_g * 100) / 100
                                        viewModel.updateCalories(calories)
                                        protein = round(firstItem.protein_g * portionSize / firstItem.serving_size_g * 100) / 100
                                        viewModel.updateProtein(protein)
                                        fat = round(firstItem.fat_total_g * portionSize / firstItem.serving_size_g * 100) / 100
                                        viewModel.updateFat(fat)
                                        carb = round(firstItem.carbohydrates_total_g * portionSize / firstItem.serving_size_g * 100) / 100
                                        viewModel.updateCarbs(carb)

                                        println(nutritionData)
                                        println(calories)
                                    }
                                    else {
                                        // Log or handle the case where no items are returned
                                        println("Response body is empty or items list is null.")
                                        Toast.makeText(context, "Failed to retrieve information for $foodName", Toast.LENGTH_SHORT).show()

                                        calories = 0.0
                                        viewModel.updateCalories(calories)
                                        protein = 0.0
                                        viewModel.updateProtein(protein)
                                        fat = 0.0
                                        viewModel.updateFat(fat)
                                        carb = 0.0
                                        viewModel.updateCarbs(carb)
                                    }
                                }
                                else {
                                    println("Error fetching data")
                                    println(nutritionData)
                                    Toast.makeText(context, "Failed to retrieve information for $foodName", Toast.LENGTH_SHORT).show()

                                    calories = 0.0
                                    viewModel.updateCalories(calories)
                                    protein = 0.0
                                    viewModel.updateProtein(protein)
                                    fat = 0.0
                                    viewModel.updateFat(fat)
                                    carb = 0.0
                                    viewModel.updateCarbs(carb)
                                }
                            }
                        }

                        MenuButton(text = "Log it") {
                            coroutineScope.launch {
                                // Check if the meal already exists in the database
                                val existingMeal = mealDAO.getMealByFoodName(foodName)
                                if (existingMeal == null) {
                                    // Meal does not exist, insert the new meal
                                    mealDAO.insertMeal(
                                        Meal(
                                            foodName = foodName,
                                            portion = portionSize,
                                            calories = calories,
                                            protein = protein,
                                            carbohydrates = carb,
                                            fats = fat,
                                            mealType = mealType,
                                            hasPhoto = (thumbnailImage != null)
                                        )
                                    )
                                } else {
                                    // Meal already exists, you can choose to update it
                                    mealDAO.updateMeal(
                                        Meal(
                                            foodName = foodName,
                                            portion = portionSize,
                                            calories = calories,
                                            protein = protein,
                                            carbohydrates = carb,
                                            fats = fat,
                                            mealType = mealType,
                                            hasPhoto = (thumbnailImage != null)
                                        )
                                    )

                                    Toast.makeText(context, "Meal already existed, updating instead.", Toast.LENGTH_SHORT).show()
                                }

                                thumbnailImage?.let {
                                    if (foodName.isNotEmpty()) {
                                        uploadBitmapToCloudStorage(it, "$foodName.jpg", viewModel, context)
                                    }
                                }
                                if (thumbnailImage == null)
                                    onNextButtonClicked()
                            }
                        }

                        MenuButton(text = "Back") {
                            onNextButtonClicked()
                        }
                    }

                    thumbnailImage?.let { bitmap: Bitmap ->
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Captured Image",
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(100.dp)
                        )
                    }
                }
            }
            else {
                if (uiState.value.progressIndicator == 100.0f) {
                    onNextButtonClicked()
                } else {
                    Text(
                        text = "Uploading image: ${uiState.value.progressIndicator}%",
                        fontFamily = FontFamily.Serif,
                        color = Color.LightGray,
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FoodStatText(text: String) {
    Text(
        text = text,
        fontFamily = FontFamily.Serif,
        color = Color.LightGray,
        modifier = Modifier
            .padding(5.dp)
    )
}


