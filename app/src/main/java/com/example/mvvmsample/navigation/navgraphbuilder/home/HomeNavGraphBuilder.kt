package com.example.mvvmsample.navigation.navgraphbuilder.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.home.HomeScreen
import com.example.mvvmsample.utils.LocalSemanticsStrings

fun NavGraphBuilder.homeScreen() {
    composable(route = NavDestinations.Home.route) {
        val semanticsStrings = LocalSemanticsStrings.current
        HomeScreen(
            modifier = Modifier.semantics {
                testTag = semanticsStrings.homeScreen
            },
        )
    }
}
