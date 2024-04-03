package com.example.mvvmsample.ui.app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mvvmsample.core.Constants
import com.example.mvvmsample.navigation.graphs.favoriteGraph
import com.example.mvvmsample.navigation.graphs.homeGraph
import com.example.mvvmsample.navigation.graphs.settingsGraph

@Composable
fun NavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = Constants.NavGraphs.HOME,
        modifier = Modifier.padding(innerPadding),
    ) {
        homeGraph()
        favoriteGraph()
        settingsGraph()
    }
}
