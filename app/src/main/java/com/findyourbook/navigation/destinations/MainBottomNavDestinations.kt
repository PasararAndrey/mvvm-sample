package com.findyourbook.navigation.destinations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.collections.immutable.persistentListOf

sealed interface MainBottomNavDestinations : NavDestinations {
    val icon: ImageVector

    data object HomeGraph : MainBottomNavDestinations {
        override val icon: ImageVector = Icons.Default.Home
        override val route: String = "home_graph"
    }

    data object FavoriteGraph : MainBottomNavDestinations {
        override val icon: ImageVector = Icons.Default.Favorite
        override val route: String = "favorite_graph"
    }

    data object BooksGraph : MainBottomNavDestinations {
        override val icon: ImageVector = Icons.Default.Info
        override val route: String = "book_graph"
    }

    companion object {
        fun destinations() =
            persistentListOf(
                HomeGraph,
                FavoriteGraph,
                BooksGraph,
            )
    }
}
