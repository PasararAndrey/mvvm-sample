package com.example.mvvmsample.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.example.mvvmsample.navigation.screenbuilder.settings.settingsScreen
import com.example.mvvmsample.navigation.utils.MainBottomNavDestinations

fun NavGraphBuilder.settingsGraph() {
    navigation(
        startDestination = MainBottomNavDestinations.Settings.route,
        route = NavGraph.SETTINGS_GRAPH.name,
    ) {
        settingsScreen()
    }
}
