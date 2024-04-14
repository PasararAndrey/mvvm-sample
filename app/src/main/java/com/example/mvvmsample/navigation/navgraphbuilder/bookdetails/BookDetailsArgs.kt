package com.example.mvvmsample.navigation.navgraphbuilder.bookdetails

import androidx.lifecycle.SavedStateHandle
import com.example.mvvmsample.navigation.destinations.NavDestinations

class BookDetailsArgs(val favoriteId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(checkNotNull(savedStateHandle[NavDestinations.BookDetails.BOOK_ID]) as String)
}
