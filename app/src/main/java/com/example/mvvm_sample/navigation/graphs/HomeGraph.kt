package com.example.mvvm_sample.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mvvm_sample.core.Constants
import com.example.mvvm_sample.navigation.BottomNavScreens
import com.example.mvvm_sample.ui.screen.home.HomeScreen

fun NavGraphBuilder.homeGraph() {
    navigation(
        startDestination = BottomNavScreens.Home.route,
        route = Constants.NavGraphs.HOME
    ) {
        composable(route = BottomNavScreens.Home.route) {
            HomeScreen()
        }
    }
}