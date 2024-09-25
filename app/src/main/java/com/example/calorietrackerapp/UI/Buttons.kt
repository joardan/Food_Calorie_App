package com.example.calorietrackerapp.UI

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//take in parameters with orientation parameters, texts, onclick

@Preview
@Composable
fun rectangularButtonPreview(){
    Button(
        onClick = {},
        shape = RectangleShape,
        modifier = Modifier
            .size(
                width = 250.dp,
                height = 250.dp
            )
            .padding(25.dp)
    ) {
        Text(
            text = "ilikedick",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    }
}

@Preview
@Composable
fun roundedButtonPreview(){
    Button(
        onClick = {},
        modifier = Modifier
            .size(
                width = 500.dp,
                height = 250.dp
            )
            .padding(25.dp)
    ) {
        Text(
            text = "ilikedick",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    }
}

@Preview
@Composable
fun circleButtonPreview(){
    Button(
        onClick = {},
        shape = CircleShape,
        modifier = Modifier
            .size(
                width = 250.dp,
                height = 250.dp
            )
            .padding(25.dp)
    ) {
        Text(
            text = "ilikedick",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    }
}

@Composable
fun rectangularButton(height: Float, width: Float, text: String, onClick: () -> Unit){
    Button(
        onClick = onClick,
        shape = RectangleShape,
        modifier = Modifier
            .size(
                width = width.dp,
                height = height.dp
            )
            .padding(25.dp)
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    }
}


@Composable
fun roundedButton(height: Float, width: Float, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(
                width = width.dp,
                height = height.dp
            )
            .padding(25.dp)
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    }
}

@Composable
fun circleButton(height: Float, width: Float, text: String, onClick: () -> Unit){
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = Modifier
            .size(
                width = width.dp,
                height = height.dp
            )
            .padding(25.dp)
    ) {
        Text(
            text = text,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
    }
}