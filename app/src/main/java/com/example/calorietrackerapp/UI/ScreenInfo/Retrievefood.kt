package com.example.calorietrackerapp.UI.ScreenInfo


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calorietrackerapp.AppLogic.DataViewModel
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.rectangularButton
import com.example.calorietrackerapp.UI.roundedButton


@Composable
fun RetrieveFood(mealDAO: MealDAO, viewModel: DataViewModel, navController: NavController, onNextButtonClicked: () -> Unit) {
    val uiState = viewModel.uiState.collectAsState()
    var foodName by remember { mutableStateOf(uiState.value.getFoodName) }
    val context = LocalContext.current

    Surface (
        modifier = Modifier
            .fillMaxSize(1f),
        color = Color(0xFF101111)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize(1f)
        ) {
            Text(
                text = "Get Food By Name",
                fontFamily = FontFamily.Serif,
                color = Color.LightGray,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
            )

            TextField(
                value = foodName,
                onValueChange = {
                    foodName = it
                    viewModel.updateGetFoodName(foodName)
                },
                label = { Text("Food Name") },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                ),
                modifier = Modifier
                    .padding(10.dp)
            )

            Button(
                onClick = {
                    val meal = mealDAO.getMealByFoodName(foodName)

                    if (meal == null) {
                        Toast.makeText(context, "Couldn't find food with name $foodName", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        viewModel.updateGetFoodName("")
                        viewModel.updateUpdateMeal(meal)
                        onNextButtonClicked()
                    }
              },
                colors = ButtonDefaults.buttonColors(Color.LightGray),
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = "Get Food",
                    fontFamily = FontFamily.Serif,
                    color = Color.Black
                )
            }

            Button(
                onClick = {
                    navController.navigate("MainMenuScreen")
                    viewModel.updateGetFoodName("")
                },
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