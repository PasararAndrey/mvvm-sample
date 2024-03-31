package com.example.mvvm_sample.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mvvm_sample.core.Constants
import com.example.mvvm_sample.navigation.BottomNavScreens
import com.example.mvvm_sample.ui.screen.favorite.FavoriteScreen

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