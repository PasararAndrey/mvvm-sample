package com.example.mvvmsample.navigation.navgraphbuilder.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.home.HomeScreen

fun NavGraphBuilder.homeScreen() {
    composable(route = NavDestinations.Home.route) {
        HomeScreen()
    }
}
