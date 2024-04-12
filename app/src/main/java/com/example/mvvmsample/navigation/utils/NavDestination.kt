package com.example.mvvmsample.navigation.utils

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavDestination {
    val route: String

    data object FavoriteSelected : NavDestination {
        const val SOME_ARG = "ARG"
        override val route: String = "${MainBottomNavDestinations.Favorite.route}/{$SOME_ARG}"
        val arguments = listOf(navArgument(SOME_ARG) { type = NavType.StringType })
    }
}
