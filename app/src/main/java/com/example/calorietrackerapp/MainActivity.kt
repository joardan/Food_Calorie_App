package com.example.calorietrackerapp

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calorietrackerapp.UI.ScreenInfo.DailyCalorieIntakeScreen
import com.example.calorietrackerapp.UI.ScreenInfo.FoodDetailScreen
import com.example.calorietrackerapp.UI.ScreenInfo.MainMenuScreen
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var context = LocalContext.current
            val filename: String = "HARHAR.txt"
            val file = File(context.filesDir, filename)
            val fileContents = "Hello world!32132131313 asduhsauidhsauidhsaudhasdsadhashdisuahdh"
            writeToFile(context, filename, fileContents)
            /*context.openFileOutput(filename, Context.MODE_PRIVATE).use {
                it.write(fileContents.toByteArray())
            }*/
            readFromFile(context, filename)
            /*context.openFileInput(filename).bufferedReader().useLines { lines ->
                lines.fold("") { some, text ->
                    println(text).toString()
                }
            }*/
        }
    }
}