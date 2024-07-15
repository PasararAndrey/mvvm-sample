package com.findyourbook.utils

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val LocalSemantics = staticCompositionLocalOf<SemanticsStrings> { error("No semantics strings provided") }

@Immutable
data class SemanticsStrings(
    val homeScreen: String = "Home screen",
    val bookDetailsScreen: String = "Book details screen",
    val booksScreen: String = "Books screen",
    val favoriteScreen: String = "Favorite screen",
    val bookDetailsImage: String = "Book image",
    val bookDetailsImageError: String = "Loading image was not successful",
    val bookDetailsTitle: String = "Book details title",
    val bookDetailsAuthors: String = "Book details authors",
    val bookDetailsDescription: String = "Book details description",
    val bookDetailsRating: String = "Book details rating",
    val bookDetailsFavoriteIcon: String = "Book details favorite icon",
    val bookDetailsLoadingIndicator: String = "Book details loading indicator",
    val booksList: String = "Books list",
    val booksListLoadingIndicator: String = "Books list loading indicator",
    val favoriteBooksLoadingIndicator: String = "Favorite books loading indicator",
)

val ImageVectorSemantic = SemanticsPropertyKey<ImageVector>("ImageVector")
var SemanticsPropertyReceiver.imageVector by ImageVectorSemantic
