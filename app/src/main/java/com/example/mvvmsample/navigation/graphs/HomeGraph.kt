package com.example.mvvmsample.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mvvmsample.core.Constants
import com.example.mvvmsample.navigation.BottomNavScreens
import com.example.mvvmsample.ui.screen.home.HomeScreen

fun NavGraphBuilder.homeGraph() {
    navigation(
        startDestination = BottomNavScreens.Home.route,
        route = Constants.NavGraphs.HOME,
    ) {
        composable(route = BottomNavScreens.Home.route) {
            HomeScreen()
        }
    }
}
