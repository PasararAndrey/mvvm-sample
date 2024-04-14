package com.example.mvvmsample.navigation.navgraphbuilder.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.settings.FavoriteScreen

fun NavGraphBuilder.favoriteScreen() {
    composable(route = NavDestinations.Favorite.route) {
        FavoriteScreen(NavDestinations.Favorite.route)
    }
}
