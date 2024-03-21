package com.example.mvvm_sample.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mvvm_sample.core.Constants
import com.example.mvvm_sample.ui.screen.favorite.FavoriteScreen
import com.example.mvvm_sample.ui.screen.home.HomeScreen
import com.example.mvvm_sample.ui.screen.settings.SettingsScreen

sealed interface BottomNavScreens : NavDestinationWithIcon {
    data object Home : BottomNavScreens {
        override val icon: ImageVector = Icons.Default.Home
        override val route: String = Constants.Routes.HOME
        override val screen: @Composable () -> Unit = { HomeScreen() }
    }

    data object Settings : BottomNavScreens {
        override val icon: ImageVector = Icons.Default.Settings
        override val route: String = Constants.Routes.SETTINGS
        override val screen: @Composable () -> Unit = { SettingsScreen() }
    }

    data object Favorite : BottomNavScreens {
        override val icon: ImageVector = Icons.Default.Favorite
        override val route: String = Constants.Routes.FAVORITE
        override val screen: @Composable () -> Unit = { FavoriteScreen() }
    }
}

fun BottomNavScreens() = listOf(
    BottomNavScreens.Home,
    BottomNavScreens.Settings,
    BottomNavScreens.Favorite
)