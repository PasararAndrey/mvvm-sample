package com.example.mvvmsample.navigation.navgraphbuilder.bookdetails

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.bookdetails.BookDetailsScreen
import com.example.mvvmsample.ui.screen.bookdetails.BookDetailsViewModel
import com.example.mvvmsample.utils.LocalSemanticsStrings

fun NavGraphBuilder.favoriteBookDetailsScreen() {
    composable(
        route = NavDestinations.BookDetails.favoriteRoute,
        arguments = NavDestinations.BookDetails.arguments,
    ) {
        val viewModel: BookDetailsViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val semanticsStrings = LocalSemanticsStrings.current
        BookDetailsScreen(
            uiState,
            onFavorite = viewModel::onFavoriteChange,
            modifier = Modifier.semantics {
                testTag = semanticsStrings.bookDetailsScreen
            },
        )
    }
}
