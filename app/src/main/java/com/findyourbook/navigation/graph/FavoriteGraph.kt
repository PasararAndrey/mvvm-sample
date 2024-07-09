package com.findyourbook.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.findyourbook.navigation.destinations.MainBottomNavDestinations
import com.findyourbook.navigation.destinations.NavDestinations
import com.findyourbook.navigation.navgraphbuilder.bookdetails.favoriteBookDetailsScreen
import com.findyourbook.navigation.navgraphbuilder.favorite.favoriteScreen
import com.findyourbook.navigation.utils.navigateToFavoriteBookDetails

fun NavGraphBuilder.favoriteGraph(navController: NavController) {
    navigation(
        startDestination = NavDestinations.Favorite.route,
        route = MainBottomNavDestinations.FavoriteGraph.route,
    ) {
        favoriteScreen { bookId -> navController.navigateToFavoriteBookDetails(bookId) }
        favoriteBookDetailsScreen()
    }
}
