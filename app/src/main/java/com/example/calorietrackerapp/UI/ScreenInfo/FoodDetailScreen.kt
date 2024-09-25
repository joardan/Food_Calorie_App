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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietrackerapp.UI.*

@Preview
@Composable
fun FoodDetailScreen() {
    var width = 200.0
    var height = 100.0

    var MT: String = ""
    var FN: String = ""
    var PS: Float = 0.0F

    var mealType by remember { mutableStateOf(MT) }
    var foodName by remember { mutableStateOf(FN) }
    var portionSize by remember { mutableStateOf(PS) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = mealType,
            onValueChange = { mealType = it },
            label = { Text("Meal Type") },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontSize = 35.sp
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
                fontSize = 35.sp
            ),
            modifier = Modifier
                .size(width.dp, height.dp)
        )

        TextField(
            value = portionSize.toString(),
            onValueChange = { portionSize = it.toFloat() },
            label = { Text("Portion Size (Float)") },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontSize = 35.sp
            ),
            modifier = Modifier
                .size(width.dp, height.dp)
        )

        rectangularButton(height = height.toFloat(), width = width.toFloat(), text = "Take Photo!", onClick = {})
        rectangularButton(height = height.toFloat(), width = width.toFloat(), text = "Upload Photo!", onClick = {})
        rectangularButton(height = height.toFloat(), width = width.toFloat(), text = "LOG IT!", onClick = {})
    }
}