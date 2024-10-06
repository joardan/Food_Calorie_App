package com.example.calorietrackerapp.UI.ScreenInfo

import android.graphics.Bitmap
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calorietrackerapp.AppLogic.uploadBitmapToCloudStorage

@Composable
fun UploadScreen() {
    // Will need to implement the view model later so that image doesn't disappear when screen is rotated
    var thumbnailImage by remember { mutableStateOf<Bitmap?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            thumbnailImage = bitmap
            // Update view model
        }
        else {
            // Update view model
        }
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        Button(
            onClick = {
                cameraLauncher.launch()
            },
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Capture Image",
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )
        }

        thumbnailImage?.let { bitmap: Bitmap ->
            Image (
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Captured Image",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(250.dp)
            )
        }

        Button(
            onClick = {
                thumbnailImage?.let { bitmap: Bitmap ->
                    uploadBitmapToCloudStorage(bitmap, "test.jpg")
                }
            },
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "Upload Image",
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )
        }

        Button(
            onClick = {
                // update view model so that image is null
                // navigate back to the menu scene
            },
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text (
                text = "Back",
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )
        }
    }
}