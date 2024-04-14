package com.example.mvvmsample.navigation.navgraphbuilder.books

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.books.BooksScreen
import com.example.mvvmsample.ui.screen.books.BooksViewModel

fun NavGraphBuilder.booksScreen(onNavigateToFavoriteSelected: (id: String) -> Unit) {
    composable(route = NavDestinations.Books.route) { backStackEntry ->
        val viewModel: BooksViewModel = hiltViewModel()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        BooksScreen(uiState = state, onNavigateToBookDetails = onNavigateToFavoriteSelected)
    }
}
