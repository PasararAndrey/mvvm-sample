package com.example.mvvmsample.ui.app

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmsample.navigation.utils.MainBottomNavDestinations

@Composable
fun App() {
    val rootNavController = rememberNavController()
    val bottomNavScreens = MainBottomNavDestinations.destinations()

    Scaffold(
        bottomBar = {
            BottomNavBar(bottomNavScreens, rootNavController)
        },
    ) { innerPadding ->
        AppNavHost(rootNavController, innerPadding)
    }
}
