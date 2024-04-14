package com.example.mvvmsample.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.example.mvvmsample.navigation.destinations.MainBottomNavDestinations
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.navigation.navgraphbuilder.settings.favoriteScreen

fun NavGraphBuilder.favoriteGraph() {
    navigation(
        startDestination = NavDestinations.Favorite.route,
        route = MainBottomNavDestinations.FavoriteGraph.route,
    ) {
        favoriteScreen()
    }
}
