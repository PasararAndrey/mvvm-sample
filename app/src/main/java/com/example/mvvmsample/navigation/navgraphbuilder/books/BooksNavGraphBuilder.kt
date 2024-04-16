package com.example.mvvmsample.navigation.navgraphbuilder.books

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.books.BooksScreen

fun NavGraphBuilder.booksScreen(onNavigateToFavoriteSelected: (id: String) -> Unit) {
    composable(route = NavDestinations.Books.route) {
        BooksScreen(onNavigateToBookDetails = onNavigateToFavoriteSelected)
    }
}
