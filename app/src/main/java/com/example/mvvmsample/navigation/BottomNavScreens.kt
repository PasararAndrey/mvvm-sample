package com.example.mvvmsample.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mvvmsample.core.Constants

sealed interface BottomNavScreens : NavDestinationWithIcon {
    data object Home : BottomNavScreens {
        override val icon: ImageVector = Icons.Default.Home
        override val route: String = Constants.Routes.HOME
    }

    data object Settings : BottomNavScreens {
        override val icon: ImageVector = Icons.Default.Settings
        override val route: String = Constants.Routes.SETTINGS
    }

    data object Favorite : BottomNavScreens {
        override val icon: ImageVector = Icons.Default.Favorite
        override val route: String = Constants.Routes.FAVORITE
    }
}

@Suppress("ktlint:standard:function-naming")
fun BottomNavScreens() =
    listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Settings,
        BottomNavScreens.Favorite,
    )
