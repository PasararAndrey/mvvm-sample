package com.example.mvvmsample.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.example.mvvmsample.navigation.destinations.MainBottomNavDestinations
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.navigation.navgraphbuilder.home.homeScreen

fun NavGraphBuilder.homeGraph() {
    navigation(
        startDestination = NavDestinations.Home.route,
        route = MainBottomNavDestinations.HomeGraph.route,
    ) {
        homeScreen()
    }
}
