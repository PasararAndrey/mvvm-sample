package com.example.mvvmsample.navigation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface MainBottomNavDestinations : NavDestination {
    val icon: ImageVector

    data object Home : MainBottomNavDestinations {
        override val icon: ImageVector = Icons.Default.Home
        override val route: String = "home"
    }

    data object Settings : MainBottomNavDestinations {
        override val icon: ImageVector = Icons.Default.Settings
        override val route: String = "settings"
    }

    data object Favorite : MainBottomNavDestinations {
        override val icon: ImageVector = Icons.Default.Favorite
        override val route: String = "favorite"
        const val ARGUMENT_KEY = "KEY"
    }

    companion object {
        fun destinations() =
            listOf(
                Home,
                Settings,
                Favorite,
            )
    }
}
