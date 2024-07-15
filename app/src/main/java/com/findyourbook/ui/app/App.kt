package com.findyourbook.ui.app

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.findyourbook.navigation.destinations.MainBottomNavDestinations

@SuppressLint("RestrictedApi")
@Composable
fun App() {
    val rootNavController = rememberNavController()
    val bottomNavScreens = MainBottomNavDestinations.destinations()
    AppContent(bottomNavScreens, rootNavController)
}

@Composable
fun AppContent(
    bottomNavScreens: List<MainBottomNavDestinations>,
    rootNavController: NavHostController,
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(bottomNavScreens, rootNavController)
        },
    ) { innerPadding ->
        AppNavHost(rootNavController, innerPadding)
    }
}
