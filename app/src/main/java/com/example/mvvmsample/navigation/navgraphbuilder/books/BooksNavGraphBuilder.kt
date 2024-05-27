package com.example.mvvmsample.navigation.navgraphbuilder.books

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
import com.example.mvvmsample.ui.screen.books.BooksScreen
import com.example.mvvmsample.ui.screen.books.BooksViewModel

fun NavGraphBuilder.booksScreen(onNavigateToBoolDetails: (id: Long) -> Unit) {
    composable(route = NavDestinations.Books.route) {
        val viewModel = hiltViewModel<BooksViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val context = LocalContext.current
        BooksScreen(
            uiState = uiState,
            onNavigateToBookDetails = onNavigateToBoolDetails,
            modifier = Modifier.semantics {
                contentDescription = context.getString(R.string.semantics_books_screen)
            },
        )
    }
}
