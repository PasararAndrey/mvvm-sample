package com.example.mvvmsample.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mvvmsample.core.Constants
import com.example.mvvmsample.navigation.BottomNavScreens
import com.example.mvvmsample.ui.screen.favorite.FavoriteScreen

fun NavGraphBuilder.favoriteGraph() {
    navigation(
        startDestination = BottomNavScreens.Favorite.route,
        route = Constants.NavGraphs.FAVORITE
    ) {
        composable(route = BottomNavScreens.Favorite.route) {
            FavoriteScreen()
        }
    }
}
