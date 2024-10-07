package com.example.calorietrackerapp.API

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking

import kotlinx.serialization.Serializable

@Serializable
data class NutritionResponse(
    val items: List<NutritionItem>
)

@Serializable
data class NutritionItem(
    val sugar_g: Double,
    val fiber_g: Double,
    val serving_size_g: Double,
    val sodium_mg: Int,
    val name: String,
    val potassium_mg: Int,
    val fat_saturated_g: Double,
    val fat_total_g: Double,
    val calories: Double,
    val cholesterol_mg: Int,
    val protein_g: Double,
    val carbohydrates_total_g: Double
)