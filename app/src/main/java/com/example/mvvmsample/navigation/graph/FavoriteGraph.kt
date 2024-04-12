package com.example.mvvmsample.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.mvvmsample.navigation.screenbuilder.favorite.favoriteScreen
import com.example.mvvmsample.navigation.screenbuilder.favoriteselected.favoriteSelectedScreen
import com.example.mvvmsample.navigation.utils.MainBottomNavDestinations
import com.example.mvvmsample.navigation.utils.navigateBackToFavoriteFromSelectedFavorite
import com.example.mvvmsample.navigation.utils.navigateToFavoriteSelected

fun NavGraphBuilder.favoriteGraph(navController: NavController) {
    navigation(
        startDestination = MainBottomNavDestinations.Favorite.route,
        route = NavGraph.FAVORITE_GRAPH.name,
    ) {
        favoriteScreen { navController.navigateToFavoriteSelected(it) }
        favoriteSelectedScreen { navController.navigateBackToFavoriteFromSelectedFavorite(it) }
    }
}
