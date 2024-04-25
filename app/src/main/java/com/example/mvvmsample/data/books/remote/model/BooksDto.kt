package com.example.mvvmsample.data.books.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksDto(
    @SerialName("available")
    val available: Int,
    @SerialName("books")
    val books: List<List<BookDto>>,
    @SerialName("number")
    val number: Int,
    @SerialName("offset")
    val offset: Int,
) {
    @Serializable
    data class BookDto(
        @SerialName("id")
        val id: Long,
        @SerialName("image")
        val image: String,
        @SerialName("subtitle")
        val subtitle: String? = null,
        @SerialName("title")
        val title: String,
    )
}
