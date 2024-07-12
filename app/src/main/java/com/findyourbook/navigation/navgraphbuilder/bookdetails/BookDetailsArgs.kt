package com.findyourbook.navigation.navgraphbuilder.bookdetails

import androidx.lifecycle.SavedStateHandle
import com.findyourbook.navigation.destinations.NavDestinations

class BookDetailsArgs(val bookId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(checkNotNull(savedStateHandle[NavDestinations.BookDetails.BOOK_ID]) as String)
}
