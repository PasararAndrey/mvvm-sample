package com.example.mvvmsample.navigation.navgraphbuilder.settings

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.R
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.favorite.FavoriteScreen
import com.example.mvvmsample.ui.screen.favorite.FavoriteViewModel

fun NavGraphBuilder.favoriteScreen(onNavigateToBoolDetails: (id: Long) -> Unit) {
    composable(route = NavDestinations.Favorite.route) {
        val viewModel: FavoriteViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val context = LocalContext.current
        FavoriteScreen(
            uiState = uiState,
            onNavigateToBookDetails = onNavigateToBoolDetails,
            modifier = Modifier.semantics {
                contentDescription = context.getString(R.string.semantics_favorite_screen)
            },
        )
    }
}
