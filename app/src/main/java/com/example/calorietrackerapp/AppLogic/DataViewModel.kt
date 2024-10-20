package com.example.calorietrackerapp.AppLogic

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.example.calorietrackerapp.Database.Meal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DataViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(DataUIState())
    val uiState = _uiState.asStateFlow()

    fun updateProgressIndicator(pProgressIndicator: Float) {
        _uiState.update { currentState -> currentState.copy(progressIndicator = pProgressIndicator) }
    }

    fun updateMealType(pMealType: String) {
        _uiState.update { currentState -> currentState.copy(mealType = pMealType) }
    }

    fun updateMealName(pMealName: String) {
        _uiState.update { currentState -> currentState.copy(mealName = pMealName) }
    }

    fun updatePortionSize(pPortionSize: Double) {
        _uiState.update { currentState -> currentState.copy(portionSize = pPortionSize) }
    }

    fun updateGetFoodName(pGetFoodName: String) {
        _uiState.update { currentState -> currentState.copy(getFoodName = pGetFoodName) }
    }

    fun updateCalories(pCalories: Double) {
        _uiState.update { currentState -> currentState.copy(calories = pCalories) }
    }

    fun updateProtein(pProtein: Double) {
        _uiState.update { currentState -> currentState.copy(protein = pProtein) }
    }

    fun updateFat(pFat: Double) {
        _uiState.update { currentState -> currentState.copy(fat = pFat) }
    }

    fun updateCarbs(pCarbs: Double) {
        _uiState.update { currentState -> currentState.copy(carbs = pCarbs) }
    }

    fun updateBitmap(pBitmap: Bitmap?) {
        _uiState.update { currentState -> currentState.copy(bitmap = pBitmap) }
    }

    fun updateSelectedMeal(pSelectedMeal: Meal?) {
        _uiState.update { currentState -> currentState.copy(selectMeal = pSelectedMeal) }
    }

    fun updateUpdateMeal(pUpdateMeal: Meal?) {
        _uiState.update { currentState -> currentState.copy(updateMeal = pUpdateMeal) }
    }
}