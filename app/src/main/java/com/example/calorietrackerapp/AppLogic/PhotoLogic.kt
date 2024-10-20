package com.example.calorietrackerapp.AppLogic

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import java.io.ByteArrayOutputStream
import com.google.firebase.storage.component1
import com.google.firebase.storage.component2
import kotlin.math.round

fun uploadBitmapToCloudStorage(bitmap: Bitmap, imageName: String, viewModel: DataViewModel, context: Context) {
    val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("images/$imageName")

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()

    // Performs the update task
    val uploadTask = imageRef.putBytes(data)
    uploadTask.addOnFailureListener {
        viewModel.updateProgressIndicator(100f)
        Toast.makeText(context, "Failed upload.", Toast.LENGTH_SHORT).show()
    }.addOnSuccessListener {
        Toast.makeText(context, "Successful upload.", Toast.LENGTH_SHORT).show()
    }.addOnProgressListener { (transferredBytes, totalBytes) ->
        val progress = round(transferredBytes.toFloat() / totalBytes.toFloat() * 10000f) / 100f
        viewModel.updateProgressIndicator(progress)
    }
}

fun getBitmapFromStorage(imageName: String) : Bitmap? {
    val storageRef = Firebase.storage.reference
    var bitmapToReturn: Bitmap? = null
    try {
        val imageRef = storageRef.child("images/$imageName")
        val ONE_MEGABYTE: Long = 1024 * 1024

        val task = imageRef.getBytes(ONE_MEGABYTE)
        while (!task.isComplete) {
            // This loop prevents the task from
        }

        task.result?.let { byteArray: ByteArray ->
            bitmapToReturn = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }
    }
    catch (error: Error) {
        Log.d("TESTTEST", "Error : ${error.message}")
    }

    Log.d("TESTTEST", "Returned: ${ bitmapToReturn != null}")
    return bitmapToReturn
}

fun deleteImageFromStorage(imageName: String, context: Context) {
    val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("images/$imageName.jpg")

    imageRef.delete().addOnSuccessListener {
        Toast.makeText(context, "Delete From Cloud Storage Success", Toast.LENGTH_SHORT).show()
    }.addOnFailureListener {
        Toast.makeText(context, "Delete From Cloud Storage Failure: $imageName", Toast.LENGTH_SHORT).show()
    }
}