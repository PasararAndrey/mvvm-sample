package com.example.mvvmsample.ui.screen.bookdetails.model

import com.example.mvvmsample.model.BookModel
import javax.annotation.concurrent.Immutable

@Immutable
data class BookDetailsUI(
    val bookId: String = "",
    val title: String? = "",
    val image: String? = "",
    val description: String? = "",
    val authors: List<String>? = emptyList(),
    val rating: Double? = null,
    val isFavorite: Boolean = false,
) {
    companion object {
        fun fromModel(model: BookModel) =
            with(model) {
                BookDetailsUI(
                    bookId = id.toString(),
                    title = title,
                    image = image,
                    description = description,
                    authors = authors?.mapNotNull { author -> author.name },
                    rating = rating,
                    isFavorite = isFavorite,
                )
            }
    }
}
