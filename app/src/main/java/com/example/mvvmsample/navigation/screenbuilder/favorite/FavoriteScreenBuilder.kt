package com.example.mvvmsample.navigation.screenbuilder.favorite

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.utils.MainBottomNavDestinations
import com.example.mvvmsample.ui.screen.favorite.FavoriteScreen
import com.example.mvvmsample.ui.screen.favorite.FavoriteViewModel

fun NavGraphBuilder.favoriteScreen(onNavigateToFavoriteSelected: (id: String) -> Unit) {
    composable(route = MainBottomNavDestinations.Favorite.route) { backStackEntry ->
        val number by backStackEntry.savedStateHandle.getStateFlow<String?>(
            MainBottomNavDestinations.Favorite.ARGUMENT_KEY,
            null,
        ).collectAsStateWithLifecycle()
        val viewModel: FavoriteViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        LaunchedEffect(number) {
            number?.let { viewModel.receiveNumber(it) }
            backStackEntry.savedStateHandle.remove<String>(MainBottomNavDestinations.Favorite.ARGUMENT_KEY)
        }
        FavoriteScreen(uiState = state, onNavigateToSelectedFavorite = onNavigateToFavoriteSelected)
    }
}
