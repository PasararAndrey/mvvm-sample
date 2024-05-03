package com.example.mvvmsample.navigation.navgraphbuilder.settings

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.favorite.FavoriteScreen
import com.example.mvvmsample.ui.screen.favorite.FavoriteViewModel

fun NavGraphBuilder.favoriteScreen(onNavigateToBoolDetails: (id: Long) -> Unit) {
    composable(route = NavDestinations.Favorite.route) {
        val viewModel: FavoriteViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        FavoriteScreen(uiState = uiState, onNavigateToBookDetails = onNavigateToBoolDetails)
    }
}
