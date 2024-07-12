package com.findyourbook.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.findyourbook.navigation.destinations.MainBottomNavDestinations
import com.findyourbook.navigation.destinations.NavDestinations
import com.findyourbook.navigation.navgraphbuilder.bookdetails.bookDetailsScreen
import com.findyourbook.navigation.navgraphbuilder.books.booksScreen
import com.findyourbook.navigation.utils.navigateToBookDetails

fun NavGraphBuilder.booksGraph(navController: NavController) {
    navigation(
        startDestination = NavDestinations.Books.route,
        route = MainBottomNavDestinations.BooksGraph.route,
    ) {
        booksScreen { bookId -> navController.navigateToBookDetails(bookId) }
        bookDetailsScreen()
    }
}
