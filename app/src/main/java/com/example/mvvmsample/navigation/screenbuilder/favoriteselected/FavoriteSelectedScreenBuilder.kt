package com.example.mvvmsample.navigation.screenbuilder.favoriteselected

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.utils.NavDestination
import com.example.mvvmsample.ui.screen.favoriteselected.FavoriteSelectedScreen
import com.example.mvvmsample.ui.screen.favoriteselected.FavoriteSelectedViewModel

fun NavGraphBuilder.favoriteSelectedScreen(onNavigateBackWithArgument: (id: String) -> Unit) {
    composable(
        route = NavDestination.FavoriteSelected.route,
        arguments = NavDestination.FavoriteSelected.arguments,
    ) {
        val viewModel: FavoriteSelectedViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        FavoriteSelectedScreen(uiState, navigateBackWithArgument = onNavigateBackWithArgument)
    }
}
