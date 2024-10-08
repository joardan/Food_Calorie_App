package com.example.calorietrackerapp.UI.ScreenInfo


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietrackerapp.UI.rectangularButton
import com.example.calorietrackerapp.UI.roundedButton


@Composable
fun RetrieveFood(onNextButtonClicked: (FoodName: String) -> Unit) {
    var size: Float = 175.0F
    var foodName by remember { mutableStateOf("") }

    Column {
        TextField(
            value = foodName,
            onValueChange = { foodName = it },
            label = { Text("Food Name") },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            ),
            modifier = Modifier
                .size(size.dp, size.dp)
        )

        roundedButton(height = size, width = size, text = "GET FOOD", onClick = { onNextButtonClicked(foodName) })
    }


}