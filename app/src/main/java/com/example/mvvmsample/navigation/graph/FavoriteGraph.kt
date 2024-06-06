package com.example.mvvmsample.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.example.mvvmsample.navigation.destinations.MainBottomNavDestinations
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.navigation.navgraphbuilder.bookdetails.favoriteBookDetailsScreen
import com.example.mvvmsample.navigation.navgraphbuilder.favorite.favoriteScreen
import com.example.mvvmsample.navigation.utils.navigateToFavoriteBookDetails

fun NavGraphBuilder.favoriteGraph(navController: NavController) {
    navigation(
        startDestination = NavDestinations.Favorite.route,
        route = MainBottomNavDestinations.FavoriteGraph.route,
    ) {
        favoriteScreen { bookId -> navController.navigateToFavoriteBookDetails(bookId) }
        favoriteBookDetailsScreen()
    }
}
