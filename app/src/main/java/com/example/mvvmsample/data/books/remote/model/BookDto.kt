package com.example.mvvmsample.data.books.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
