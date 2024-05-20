package com.example.mvvmsample.model

import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.example.mvvmsample.data.books.remote.model.BookDTO

data class BookModel(
    val id: Int,
    val authors: List<Author>? = null,
    val description: String? = null,
    val image: String? = null,
    val numberOfPages: Double? = null,
    val publishDate: Double? = null,
    val rating: Double? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val isFavorite: Boolean = false,
) {
    data class Author(
        val id: Int,
        val name: String?,
    )

    companion object {
        fun fromDto(dto: BookDTO): BookModel =
            with(dto) {
                BookModel(
                    id = id,
                    authors = authors?.map { Author(it.id, it.name) },
                    description = description,
                    image = image,
                    numberOfPages = numberOfPages,
                    publishDate = publishDate,
                    rating = rating?.average,
                    title = title,
                    subtitle = subtitle,
                )
            }

        fun fromEntity(entity: BookEntity) =
            with(entity) {
                BookModel(
                    id = id,
                    authors = authors?.map { Author(it.id, it.name) },
                    description = description,
                    image = image,
                    numberOfPages = numberOfPages,
                    publishDate = publishDate,
                    rating = rating,
                    title = title,
                    subtitle = subtitle,
                    isFavorite = isFavorite,
                )
            }
    }
}
