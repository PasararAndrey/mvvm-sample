package com.example.mvvmsample.navigation.destinations

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavDestinations {
    val route: String

    data object BookDetails : NavDestinations {
        const val BOOK_ID = "book_id"
        override val route: String = "${Books.route}/{$BOOK_ID}"
        val arguments = listOf(navArgument(BOOK_ID) { type = NavType.StringType })

        fun destinationWithArg(bookId: Long) = "${Books.route}/$bookId"
    }

    data object Home : NavDestinations {
        override val route: String = "home"
    }

    data object Favorite : NavDestinations {
        override val route: String = "favorite"
    }

    data object Books : NavDestinations {
        override val route: String = "book"
        const val ARGUMENT_KEY = "KEY"
    }
}
