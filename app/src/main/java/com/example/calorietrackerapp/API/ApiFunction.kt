package com.example.calorietrackerapp.API

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import com.example.calorietrackerapp.API.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface CalorieNinjaService {
    @GET("v1/nutrition")
    suspend fun getNutritionInfo(
        @Header("X-Api-Key") apiKey: String, // Add the API key as a header
        @Query("query") foodItem: String
    ): Response<NutritionResponse>
}