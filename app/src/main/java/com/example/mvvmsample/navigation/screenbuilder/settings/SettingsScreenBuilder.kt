package com.example.mvvmsample.navigation.screenbuilder.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.utils.MainBottomNavDestinations
import com.example.mvvmsample.ui.screen.settings.SettingsScreen

fun NavGraphBuilder.settingsScreen() {
    composable(route = MainBottomNavDestinations.Settings.route) {
        SettingsScreen(MainBottomNavDestinations.Settings.route)
    }
}
