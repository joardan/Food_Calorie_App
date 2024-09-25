package com.example.calorietrackerapp

import java.io.File
import java.nio.file.Files
import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


fun writeToFile(context: Context, filename: String, content: String) {
    context.openFileOutput(filename, Context.MODE_PRIVATE).use { it.write(content.toByteArray())
    }
}

@Composable
fun FileWriteExample() {
    var files: Array<String> = LocalContext.current.fileList()
    val context = LocalContext.current
    val filename = "MYRETARDEDFILE"
    val content = "HHI IM RETARDED"

    Button(onClick = { writeToFile(context, filename, content);
        File.createTempFile("adasdsadasd", ".txt", context.cacheDir)}) {
        Text(text = "Write to file")
    }
}

fun readFromFile(context: Context, filename: String) {
    context.openFileInput(filename).bufferedReader().useLines { lines ->
        lines.fold("") { some, text ->
            println(text).toString()
        }
    }
}