package com.example.calorietrackerapp.AppLogic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import java.io.ByteArrayOutputStream

fun uploadBitmapToCloudStorage(bitmap: Bitmap, imageName: String) {
    val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("images/$imageName")

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()

    var uploadTask = imageRef.putBytes(data)
    uploadTask.addOnFailureListener {
        Log.d("TESTTEST", "Upload Failure")
    }.addOnSuccessListener {
        Log.d("TESTTEST", "Upload Success")
    }
}

fun getBitmapFromStorage(imageName: String) : Bitmap? {
    val storageRef = Firebase.storage.reference
    //val pathReference = storageRef.child("images/$imageName")
    //val gsReference = Firebase.storage.getReferenceFromUrl("gs://bucket/images/$imageName")

    /*val httpReference = Firebase.storage.getReferenceFromUrl(
        "https://firebasestorage.googleapis.com/b/bucket/o/images%20$imageName"
    )*/

    var downloaded = false
    var bitmapToReturn: Bitmap? = null
    var imageRef = storageRef.child("images/$imageName")
    val ONE_MEGABYTE: Long = 1024 * 1024
    val task = imageRef.getBytes(ONE_MEGABYTE)

    while (!task.isComplete) {
        // This loop prevents the task from
    }

    task.result?.let { byteArray: ByteArray ->
        bitmapToReturn = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    Log.d("TESTTEST", "Returned: ${ bitmapToReturn != null}")
    return bitmapToReturn
}