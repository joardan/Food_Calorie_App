package com.example.calorietrackerapp.AppLogic

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

/*
@Composable
fun NavigationManager(//need external database?) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsState() //retrieving extcernal data in this line most likely

    NavHost(
        navController = navController,
        startDestination = ScreenList.MainMenuScreen,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )
*/
