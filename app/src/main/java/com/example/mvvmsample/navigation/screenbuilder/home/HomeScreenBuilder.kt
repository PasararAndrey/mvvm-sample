package com.example.mvvmsample.navigation.screenbuilder.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.utils.MainBottomNavDestinations
import com.example.mvvmsample.ui.screen.home.HomeScreen

fun NavGraphBuilder.homeScreen() {
    composable(route = MainBottomNavDestinations.Home.route) {
        HomeScreen()
    }
}
