package com.findyourbook.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.findyourbook.navigation.destinations.MainBottomNavDestinations
import com.findyourbook.navigation.destinations.NavDestinations
import com.findyourbook.navigation.navgraphbuilder.home.homeScreen

fun NavGraphBuilder.homeGraph() {
    navigation(
        startDestination = NavDestinations.Home.route,
        route = MainBottomNavDestinations.HomeGraph.route,
    ) {
        homeScreen()
    }
}
