package com.example.mvvmsample.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.mvvmsample.navigation.destinations.MainBottomNavDestinations
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.navigation.navgraphbuilder.bookdetails.bookDetailsScreen
import com.example.mvvmsample.navigation.navgraphbuilder.books.booksScreen
import com.example.mvvmsample.navigation.utils.navigateToBookDetails

fun NavGraphBuilder.booksGraph(navController: NavController) {
    navigation(
        startDestination = NavDestinations.Books.route,
        route = MainBottomNavDestinations.BooksGraph.route,
    ) {
        booksScreen { bookId -> navController.navigateToBookDetails(bookId) }
        bookDetailsScreen()
    }
}
