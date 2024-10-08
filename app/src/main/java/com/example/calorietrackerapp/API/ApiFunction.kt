package com.example.calorietrackerapp.API


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