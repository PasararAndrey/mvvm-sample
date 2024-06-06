package com.example.mvvmsample.navigation.utils

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.mvvmsample.navigation.destinations.NavDestinations

fun NavController.navigateToBookDetails(
    bookId: Long,
    navOptions: NavOptions? = null,
) {
    this.navigate(NavDestinations.BookDetails.booksDestinationWithArg(bookId), navOptions)
}

fun NavController.navigateToFavoriteBookDetails(
    bookId: Long,
    navOptions: NavOptions? = null,
) {
    this.navigate(NavDestinations.BookDetails.favoriteBookDestinationWithArg(bookId), navOptions)
}

fun <T> NavController.navigateBackWithResult(
    key: String,
    value: T?,
) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)
    popBackStack()
}
