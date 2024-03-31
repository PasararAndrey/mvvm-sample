package com.example.mvvm_sample.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.mvvm_sample.core.Constants
import com.example.mvvm_sample.navigation.BottomNavScreens
import com.example.mvvm_sample.ui.screen.settings.SettingsScreen

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