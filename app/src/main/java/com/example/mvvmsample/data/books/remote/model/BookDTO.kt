package com.example.mvvmsample.data.books.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("authors")
    val authors: List<Author>? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("identifiers")
    val identifiers: Identifiers?,
    @SerialName("image")
    val image: String? = null,
    @SerialName("number_of_pages")
    val numberOfPages: Double? = null,
    @SerialName("publish_date")
    val publishDate: Double? = null,
    @SerialName("rating")
    val rating: Rating? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("subtitle")
    val subtitle: String? = null,
) {
    @Serializable
    data class Author(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String?,
    )

    @Serializable
    data class Identifiers(
        @SerialName("isbn_10")
        val isbn10: String? = null,
        @SerialName("isbn_13")
        val isbn13: String? = null,
        @SerialName("open_library_id")
        val openLibraryId: String? = null,
    )

    @Serializable
    data class Rating(
        @SerialName("average")
        val average: Double? = null,
    )
}
