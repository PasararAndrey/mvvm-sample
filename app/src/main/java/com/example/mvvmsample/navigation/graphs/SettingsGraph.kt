package com.example.mvvmsample.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mvvmsample.core.Constants
import com.example.mvvmsample.navigation.BottomNavScreens
import com.example.mvvmsample.ui.screen.settings.SettingsScreen

fun NavGraphBuilder.settingsGraph() {
    navigation(
        startDestination = BottomNavScreens.Settings.route,
        route = Constants.NavGraphs.SETTINGS
    ) {
        composable(route = BottomNavScreens.Settings.route) {
            SettingsScreen()
        }
    }
}
