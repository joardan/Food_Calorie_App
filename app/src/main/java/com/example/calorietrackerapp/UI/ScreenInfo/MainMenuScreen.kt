package com.example.calorietrackerapp.UI.ScreenInfo

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietrackerapp.Database.MealDAO
import com.example.calorietrackerapp.UI.*
import io.ktor.utils.io.bits.Allocator

@Composable
fun MainMenuScreen(mealDAO: MealDAO,
                   onNextButtonClickedLogMeal: () -> Unit,
                   onNextButtonClickedShowMeals: () -> Unit,
                   onNextButtonClickedDeleteUpdateMeals: () -> Unit
) {
    val portrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT

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
            Text(
                text = "WELCOME To Calorie Tracking App",
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif,
                fontSize = 25.sp,
                color = Color.LightGray
            )

            if (portrait) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MenuButton("Log Meal", onNextButtonClickedLogMeal)
                    MenuButton("Intake So Far", onNextButtonClickedShowMeals)
                    MenuButton("Delete/Update", onNextButtonClickedDeleteUpdateMeals)
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(10.dp)
                ) {
                    MenuButton("Log Meal", onNextButtonClickedLogMeal)
                    MenuButton("Intake So Far", onNextButtonClickedShowMeals)
                    MenuButton("Delete/Update", onNextButtonClickedDeleteUpdateMeals)
                }
            }

        }
    }
}

@Composable
fun MenuButton(text: String, buttonClick: () -> Unit) {
    Button (
        onClick = buttonClick,
        colors = ButtonDefaults.buttonColors(Color.LightGray),
        modifier = Modifier
            .padding(10.dp)
    ) {
        Text (
            text = text,
            fontFamily = FontFamily.Serif,
            color = Color.Black
        )
    }
}