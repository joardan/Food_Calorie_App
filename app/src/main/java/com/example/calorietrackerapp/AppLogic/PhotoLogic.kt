package com.example.calorietrackerapp.AppLogic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.telecom.Call
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import io.reactivex.Observable
import java.io.ByteArrayOutputStream
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

fun uploadBitmapToCloudStorage(bitmap: Bitmap, imageName: String) {
    val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("images/$imageName")

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()

    val uploadTask = imageRef.putBytes(data)
    uploadTask.addOnFailureListener {
        Log.d("TESTTEST", "Upload Failure")
    }.addOnSuccessListener {
        Log.d("TESTTEST", "Upload Success")
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