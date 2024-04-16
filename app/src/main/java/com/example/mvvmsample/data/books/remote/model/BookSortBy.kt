package com.example.mvvmsample.data.books.remote.model

import kotlinx.serialization.SerialName

enum class BookSortBy {
    @SerialName("publish-date")
    PUBLISH_DATE,

    @SerialName("rating")
    RATING,
}
