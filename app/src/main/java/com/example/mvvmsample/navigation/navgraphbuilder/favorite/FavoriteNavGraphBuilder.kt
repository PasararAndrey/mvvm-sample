package com.example.mvvmsample.navigation.navgraphbuilder.favorite

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.favorite.FavoriteScreen
import com.example.mvvmsample.ui.screen.favorite.FavoriteViewModel
import com.example.mvvmsample.utils.LocalSemanticsStrings

fun NavGraphBuilder.favoriteScreen(onNavigateToBoolDetails: (id: Long) -> Unit) {
    composable(route = NavDestinations.Favorite.route) {
        val viewModel: FavoriteViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val semanticsStrings = LocalSemanticsStrings.current
        FavoriteScreen(
            uiState = uiState,
            onNavigateToBookDetails = onNavigateToBoolDetails,
            modifier = Modifier.semantics {
                testTag = semanticsStrings.favoriteScreen
            },
        )
    }
}
