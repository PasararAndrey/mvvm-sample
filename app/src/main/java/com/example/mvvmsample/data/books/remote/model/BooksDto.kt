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
)
