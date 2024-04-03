package com.example.mvvmsample.ui.app

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.mvvmsample.navigation.BottomNavScreens

@Composable
fun App() {
    val navController = rememberNavController()
    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    val bottomNavScreen = BottomNavScreens()

    Scaffold(
        bottomBar = {
            BottomNavBar(bottomNavScreen, selectedItem, navController) { index ->
                selectedItem = index
            }
        },
    ) { innerPadding ->
        NavHost(navController, innerPadding)
    }
}
