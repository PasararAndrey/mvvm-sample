package com.example.mvvmsample.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class SemanticsStringsTest {
    @Test
    fun `default values are correctly set`() {
        val semanticsStrings = SemanticsStrings()

        assertEquals("Home screen", semanticsStrings.homeScreen)
        assertEquals("Book details screen", semanticsStrings.bookDetailsScreen)
        assertEquals("Books screen", semanticsStrings.booksScreen)
        assertEquals("Favorite screen", semanticsStrings.favoriteScreen)
        assertEquals("Book details title", semanticsStrings.bookDetailsTitle)
        assertEquals("Book details authors", semanticsStrings.bookDetailsAuthors)
        assertEquals("Book details description", semanticsStrings.bookDetailsDescription)
        assertEquals("Book details rating", semanticsStrings.bookDetailsRating)
        assertEquals("Book details favorite icon", semanticsStrings.bookDetailsFavoriteIcon)
        assertEquals("Book details loading indicator", semanticsStrings.bookDetailsLoadingIndicator)
        assertEquals("Books list", semanticsStrings.booksList)
        assertEquals("Books list loading indicator", semanticsStrings.booksListLoadingIndicator)
        assertEquals("Favorite books loading indicator", semanticsStrings.favoriteBooksLoadingIndicator)
    }

    @Test
    fun `custom values are correctly set`() {
        val customSemanticsStrings = SemanticsStrings(
            homeScreen = "Custom Home Screen",
            bookDetailsScreen = "Custom Book Details Screen",
            booksScreen = "Custom Books Screen",
            favoriteScreen = "Custom Favorite Screen",
            bookDetailsTitle = "Custom Book Details Title",
            bookDetailsAuthors = "Custom Book Details Authors",
            bookDetailsDescription = "Custom Book Details Description",
            bookDetailsRating = "Custom Book Details Rating",
            bookDetailsFavoriteIcon = "Custom Book Details Favorite Icon",
            bookDetailsLoadingIndicator = "Custom Book Details Loading Indicator",
            booksList = "Custom Books List",
            booksListLoadingIndicator = "Custom Books List Loading Indicator",
            favoriteBooksLoadingIndicator = "Custom Favorite Books Loading Indicator",
        )

        assertEquals("Custom Home Screen", customSemanticsStrings.homeScreen)
        assertEquals("Custom Book Details Screen", customSemanticsStrings.bookDetailsScreen)
        assertEquals("Custom Books Screen", customSemanticsStrings.booksScreen)
        assertEquals("Custom Favorite Screen", customSemanticsStrings.favoriteScreen)
        assertEquals("Custom Book Details Title", customSemanticsStrings.bookDetailsTitle)
        assertEquals("Custom Book Details Authors", customSemanticsStrings.bookDetailsAuthors)
        assertEquals("Custom Book Details Description", customSemanticsStrings.bookDetailsDescription)
        assertEquals("Custom Book Details Rating", customSemanticsStrings.bookDetailsRating)
        assertEquals("Custom Book Details Favorite Icon", customSemanticsStrings.bookDetailsFavoriteIcon)
        assertEquals("Custom Book Details Loading Indicator", customSemanticsStrings.bookDetailsLoadingIndicator)
        assertEquals("Custom Books List", customSemanticsStrings.booksList)
        assertEquals("Custom Books List Loading Indicator", customSemanticsStrings.booksListLoadingIndicator)
        assertEquals("Custom Favorite Books Loading Indicator", customSemanticsStrings.favoriteBooksLoadingIndicator)
    }
}
