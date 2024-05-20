package com.example.mvvmsample.navigation.navgraphbuilder.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.R
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.home.HomeScreen

fun NavGraphBuilder.homeScreen() {
    composable(route = NavDestinations.Home.route) {
        val context = LocalContext.current
        HomeScreen(
            modifier = Modifier.semantics {
                contentDescription = context.getString(R.string.semantics_home_screen)
            },
        )
    }
}
