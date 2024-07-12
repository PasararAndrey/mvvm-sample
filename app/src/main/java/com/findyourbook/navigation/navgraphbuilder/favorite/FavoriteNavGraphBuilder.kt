package com.findyourbook.navigation.navgraphbuilder.favorite

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.findyourbook.navigation.destinations.NavDestinations
import com.findyourbook.ui.screen.favorite.FavoriteScreen
import com.findyourbook.ui.screen.favorite.FavoriteViewModel
import com.findyourbook.utils.LocalSemantics

fun NavGraphBuilder.favoriteScreen(onNavigateToBoolDetails: (id: Long) -> Unit) {
    composable(route = NavDestinations.Favorite.route) {
        val viewModel: FavoriteViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val semanticsStrings = LocalSemantics.current
        FavoriteScreen(
            uiState = uiState,
            onNavigateToBookDetails = onNavigateToBoolDetails,
            modifier = Modifier.semantics {
                testTag = semanticsStrings.favoriteScreen
            },
        )
    }
}
