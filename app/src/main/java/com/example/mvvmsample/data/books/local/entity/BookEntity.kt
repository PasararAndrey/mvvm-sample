package com.example.mvvmsample.data.books.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmsample.data.books.remote.model.BookDTO
import kotlinx.serialization.Serializable

@Entity("books")
data class BookEntity(
    @PrimaryKey @ColumnInfo(name = "book_id") val id: Int,
    val authors: List<Author>? = null,
    val description: String? = null,
    val image: String? = null,
    val numberOfPages: Double? = null,
    val publishDate: Double? = null,
    val rating: Double? = null,
    val title: String? = null,
    val subtitle: String? = null,
) {
    @Serializable
    data class Author(
        @ColumnInfo("author_id") val id: Int,
        val name: String?,
    )

    companion object {
        fun fromDto(bookDTO: BookDTO) =
            with(bookDTO) {
                BookEntity(
                    id,
                    authors?.map { Author(it.id, it.name) },
                    description,
                    image,
                    numberOfPages,
                    publishDate,
                    rating?.average,
                    title,
                    subtitle,
                )
            }
    }
}
