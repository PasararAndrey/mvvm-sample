package com.example.mvvmsample.navigation.navgraphbuilder.books

import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.books.BooksScreen
import com.example.mvvmsample.ui.screen.books.BooksViewModel
import com.example.mvvmsample.utils.LocalSemantics

fun NavGraphBuilder.booksScreen(onNavigateToBoolDetails: (id: Long) -> Unit) {
    composable(route = NavDestinations.Books.route) {
        val viewModel = hiltViewModel<BooksViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val semanticsStrings = LocalSemantics.current
        BooksScreen(
            uiState = uiState,
            onNavigateToBookDetails = onNavigateToBoolDetails,
            modifier = Modifier.semantics {
                contentDescription = semanticsStrings.booksScreen
            },
        )
    }
}
