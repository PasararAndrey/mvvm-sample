package com.example.mvvmsample.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.example.mvvmsample.navigation.screenbuilder.home.homeScreen
import com.example.mvvmsample.navigation.utils.MainBottomNavDestinations

fun NavGraphBuilder.homeGraph() {
    navigation(
        startDestination = MainBottomNavDestinations.Home.route,
        route = NavGraph.HOME_GRAPH.name,
    ) {
        homeScreen()
    }
}
