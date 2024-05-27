package com.example.mvvmsample.ui.app

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mvvmsample.navigation.destinations.MainBottomNavDestinations

@SuppressLint("RestrictedApi")
@Composable
fun App() {
    val rootNavController = rememberNavController()
    val bottomNavScreens = MainBottomNavDestinations.destinations()

    rootNavController.addOnDestinationChangedListener { controller, _, _ ->
        val routes = controller.currentBackStack.value.map { it.destination.route }.joinToString(" -> ")
        Log.d("BackStackLog", "BackStack: $routes")
    }

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
