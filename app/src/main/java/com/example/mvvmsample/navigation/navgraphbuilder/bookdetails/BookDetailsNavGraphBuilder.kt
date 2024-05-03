package com.example.mvvmsample.navigation.navgraphbuilder.bookdetails

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.bookdetails.BookDetailsScreen
import com.example.mvvmsample.ui.screen.bookdetails.BookDetailsViewModel

fun NavGraphBuilder.bookDetailsScreen() {
    composable(
        route = NavDestinations.BookDetails.route,
        arguments = NavDestinations.BookDetails.arguments,
    ) {
        val viewModel: BookDetailsViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        BookDetailsScreen(uiState, onFavorite = viewModel::onFavoriteChange)
    }
}
