package com.example.mvvmsample.navigation.navgraphbuilder.bookdetails

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
import com.example.mvvmsample.ui.screen.bookdetails.BookDetailsScreen
import com.example.mvvmsample.ui.screen.bookdetails.BookDetailsViewModel

fun NavGraphBuilder.bookDetailsScreen() {
    composable(
        route = NavDestinations.BookDetails.route,
        arguments = NavDestinations.BookDetails.arguments,
    ) {
        val viewModel: BookDetailsViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val context = LocalContext.current
        BookDetailsScreen(
            uiState,
            onFavorite = viewModel::onFavoriteChange,
            modifier = Modifier.semantics {
                contentDescription = context.getString(R.string.semantics_book_details_screen)
            },
        )
    }
}
