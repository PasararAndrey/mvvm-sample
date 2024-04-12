package com.example.mvvmsample.ui.app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mvvmsample.navigation.graph.NavGraph
import com.example.mvvmsample.navigation.graph.favoriteGraph
import com.example.mvvmsample.navigation.graph.homeGraph
import com.example.mvvmsample.navigation.graph.settingsGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = NavGraph.HOME_GRAPH.name,
        modifier = Modifier.padding(innerPadding),
    ) {
        homeGraph()
        settingsGraph()
        favoriteGraph(navController)
    }
}
