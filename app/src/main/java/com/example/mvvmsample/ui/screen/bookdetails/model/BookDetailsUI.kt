package com.example.mvvmsample.ui.screen.bookdetails.model

import com.example.mvvmsample.data.books.remote.model.BookByIdDTO
import javax.annotation.concurrent.Immutable

@Immutable
data class BookDetailsUI(
    val bookId: String = "",
    val title: String? = null,
    val image: String? = null,
    val description: String? = null,
    val authors: List<String>? = null,
    val rating: Double? = null,
) {
    companion object {
        fun fromDto(dto: BookByIdDTO) =
            with(dto) {
                BookDetailsUI(
                    bookId = id.toString(),
                    title = title,
                    image = image,
                    description = description,
                    authors = authors?.mapNotNull { author -> author.name },
                    rating = rating?.average,
                )
            }
    }
}
