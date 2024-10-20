package com.example.calorietrackerapp.UI.ScreenInfo

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calorietrackerapp.AppLogic.DataViewModel
import com.example.calorietrackerapp.AppLogic.getBitmapFromStorage
import com.example.calorietrackerapp.Database.Meal
import com.example.calorietrackerapp.Database.MealDAO

@Composable
fun DailyCalorieIntakeScreen(mealDAO: MealDAO, viewModel: DataViewModel, navController: NavController, onNextButtonClicked: () -> Unit) {
    val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    val uiState = viewModel.uiState.collectAsState()

    var mealList = mealDAO.getAllMeals()
    var totalCals = mealDAO.getTotalCalories()
    var totalProt = mealDAO.getTotalProtein()
    var totalFat = mealDAO.getTotalFats()
    var totalCarbs = mealDAO.getTotalCarbohydrates()

    var selectedMeal by remember { mutableStateOf(uiState.value.selectMeal) }

    Surface (
        modifier = Modifier
            .fillMaxSize(1f),
        color = Color(0xFF101111)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(getHorizontalPadding(portrait), getVerticalPadding(portrait)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text (
                text = "Daily Calorie Intake Screen",
                fontSize = 25.sp,
                color = Color.LightGray,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
            )

            Text (
                text = "Totals",
                fontSize = 15.sp,
                color = Color.LightGray,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(5.dp)
            )

            if (portrait) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    TotalText(text = "Calories: $totalCals", widthFraction = 0.5f)
                    TotalText(text = "Fats: $totalFat", widthFraction = 1f)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    TotalText(text = "Protein: $totalProt", widthFraction = 0.5f)
                    TotalText(text = "Carbs: $totalCarbs", widthFraction = 1f)
                }

                Button(
                    onClick = onNextButtonClicked,
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = "BACK",
                        fontFamily = FontFamily.Serif,
                        color = Color.Black
                    )
                }

                DisplayMeal(meal = null) {
                    // Do Nothing
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(mealList) { meal ->
                        DisplayMeal(meal = meal) {
                            selectedMeal = meal
                            viewModel.updateSelectedMeal(selectedMeal)
                        }
                    }
                }

                if (selectedMeal == null) {
                    Text(
                        text = "No Meal Selected",
                        fontFamily = FontFamily.Serif,
                        color = Color.LightGray,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .border(1.dp, Color.LightGray)
                            .wrapContentHeight(Alignment.CenterVertically)
                    )
                }
                selectedMeal?.let { meal ->
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .border(1.dp, Color.Green)
                    ) {
                        TotalText(text = meal.foodName, widthFraction = 1f)
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                        ) {
                            TotalText(text = "Meal Type: ${meal.foodName}", widthFraction = 0.5f)
                            TotalText(text = "Portion Size: ${meal.portion}", widthFraction = 1f)
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                        ) {
                            TotalText(text = "Calories: ${meal.calories}", widthFraction = 0.5f)
                            TotalText(text = "Fats: ${meal.fats}", widthFraction = 1f)
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                        ) {
                            TotalText(text = "Protein: ${meal.protein}", widthFraction = 0.5f)
                            TotalText(text = "Carbs: ${meal.carbohydrates}", widthFraction = 1f)
                        }

                        if (meal.hasPhoto) {
                            val imageBitmap: Bitmap? = getBitmapFromStorage("${meal.foodName}.jpg")
                            imageBitmap?.let {
                                Image(
                                    bitmap = it.asImageBitmap(),
                                    contentDescription = "Food Image",
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .height(100.dp)
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                        ) {
                            Button(
                                onClick = {
                                    selectedMeal = null
                                    viewModel.updateSelectedMeal(selectedMeal)
                                },
                                colors = ButtonDefaults.buttonColors(Color.LightGray),
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = "Unselect",
                                    fontFamily = FontFamily.Serif,
                                    color = Color.Black
                                )
                            }

                            Button(
                                onClick = {
                                    viewModel.updateUpdateMeal(selectedMeal)
                                    navController.navigate("DeleteUpdateScreen")
                                },
                                colors = ButtonDefaults.buttonColors(Color.LightGray),
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = "Delete/Update",
                                    fontFamily = FontFamily.Serif,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
            else {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                ) {
                    TotalText(text = "Calories: $totalCals", widthFraction = 0.25f)
                    TotalText(text = "Protein: $totalProt", widthFraction = 1/3f)
                    TotalText(text = "Fats: $totalFat", widthFraction = 0.5f)
                    TotalText(text = "Carbs: $totalCarbs", widthFraction = 1f)
                }

                Button(
                    onClick = onNextButtonClicked,
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = "BACK",
                        fontFamily = FontFamily.Serif,
                        color = Color.Black
                    )
                }

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .padding(5.dp, 0.dp)
                ) {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight(1f)
                    ) {
                        DisplayMeal(meal = null) {
                            // Do Nothing
                        }
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(mealList) { meal ->
                                DisplayMeal(meal = meal) {
                                    selectedMeal = meal
                                    viewModel.updateSelectedMeal(selectedMeal)
                                }
                            }
                        }
                    }

                    if (selectedMeal == null) {
                        Text(
                            text = "No Meal Selected",
                            fontFamily = FontFamily.Serif,
                            color = Color.LightGray,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxSize(1f)
                                .border(1.dp, Color.LightGray)
                                .wrapContentHeight(Alignment.CenterVertically)
                        )
                    }
                    selectedMeal?.let { meal ->
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxSize(1f)
                                .border(1.dp, Color.Green)
                        ) {
                            TotalText(text = meal.foodName, widthFraction = 1f)
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                            ) {
                                TotalText(
                                    text = "Meal Type: ${meal.foodName}",
                                    widthFraction = 0.5f
                                )
                                TotalText(
                                    text = "Portion Size: ${meal.portion}",
                                    widthFraction = 1f
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                            ) {
                                TotalText(text = "Calories: ${meal.calories}", widthFraction = 0.5f)
                                TotalText(text = "Fats: ${meal.fats}", widthFraction = 1f)
                            }
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                            ) {
                                TotalText(text = "Protein: ${meal.protein}", widthFraction = 0.5f)
                                TotalText(text = "Carbs: ${meal.carbohydrates}", widthFraction = 1f)
                            }

                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                            ) {

                                if (meal.hasPhoto) {
                                    val imageBitmap: Bitmap? =
                                        getBitmapFromStorage("${meal.foodName}.jpg")
                                    imageBitmap?.let {
                                        Image(
                                            bitmap = it.asImageBitmap(),
                                            contentDescription = "Food Image",
                                            modifier = Modifier
                                                .height(100.dp)
                                        )
                                    }
                                }

                                Button(
                                    onClick = {
                                        selectedMeal = null
                                        viewModel.updateSelectedMeal(selectedMeal)
                                    },
                                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = "Unselect",
                                        fontFamily = FontFamily.Serif,
                                        color = Color.Black
                                    )
                                }

                                Button(
                                    onClick = {
                                        viewModel.updateUpdateMeal(selectedMeal)
                                        navController.navigate("DeleteUpdateScreen")
                                    },
                                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                                    modifier = Modifier
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = "Delete/Update",
                                        fontFamily = FontFamily.Serif,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TotalText(text: String, widthFraction: Float) {
    Text(
        text = text,
        fontFamily = FontFamily.Serif,
        color = Color.LightGray,
        fontSize = 15.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth(widthFraction)
            .padding(5.dp)
    )
}

@Composable
fun DisplayMeal(meal: Meal?, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth(1f)
            .border(1.dp, Color.LightGray)
    ) {
        if (meal == null) {
            Text (
                text = "Meal Name",
                fontFamily = FontFamily.Serif,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(1/3f)
            )
            Text (
                text = "Portion Size (g)",
                fontFamily = FontFamily.Serif,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            )
            Text (
                text = "Image",
                fontFamily = FontFamily.Serif,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(1f)
            )
        }
        else {
            Text (
                text = meal.foodName,
                fontFamily = FontFamily.Serif,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(1/3f)
            )
            Text (
                text = meal.portion.toString(),
                fontFamily = FontFamily.Serif,
                color = Color.LightGray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            )

            if (meal.hasPhoto) {
                val imageBitmap: Bitmap? = getBitmapFromStorage("${meal.foodName}.jpg")
                imageBitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Food Image",
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(100.dp)
                    )
                }

                if (imageBitmap == null){
                    Text (
                        text = "No Image",
                        fontFamily = FontFamily.Serif,
                        color = Color.LightGray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                    )
                }
            }
            else {
                Text (
                    text = "No Image",
                    fontFamily = FontFamily.Serif,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                )
            }
        }
    }
}

fun getVerticalPadding(portrait: Boolean) : Dp {
    if (portrait)
        return 50.dp
    return 5.dp
}
fun getHorizontalPadding(portrait: Boolean) : Dp {
    if (portrait)
        return 5.dp
    return 40.dp
}
