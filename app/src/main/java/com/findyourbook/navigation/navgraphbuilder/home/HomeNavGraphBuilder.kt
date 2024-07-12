package com.findyourbook.navigation.navgraphbuilder.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.findyourbook.navigation.destinations.NavDestinations
import com.findyourbook.ui.screen.home.HomeScreen
import com.findyourbook.utils.LocalSemantics

fun NavGraphBuilder.homeScreen() {
    composable(route = NavDestinations.Home.route) {
        val semanticsStrings = LocalSemantics.current
        HomeScreen(
            modifier = Modifier.semantics {
                testTag = semanticsStrings.homeScreen
            },
        )
    }
}
