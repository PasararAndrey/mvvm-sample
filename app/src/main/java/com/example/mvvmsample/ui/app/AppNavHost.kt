package com.example.mvvmsample.ui.app

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mvvmsample.navigation.destinations.MainBottomNavDestinations
import com.example.mvvmsample.navigation.graph.booksGraph
import com.example.mvvmsample.navigation.graph.favoriteGraph
import com.example.mvvmsample.navigation.graph.homeGraph

@Composable
fun AppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = MainBottomNavDestinations.HomeGraph.route,
        modifier = Modifier.padding(innerPadding),
    ) {
        homeGraph()
        favoriteGraph(navController)
        booksGraph(navController)
    }
}
