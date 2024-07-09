package com.findyourbook.ui.screen.books.model

import com.findyourbook.data.books.local.entity.BookPreviewEntity
import com.findyourbook.data.books.remote.model.BooksDto

data class BooksUI(
    val id: Long,
    val title: String,
    val image: String,
    val subtitle: String?,
) {
    companion object {
        fun fromEntity(bookEntity: BookPreviewEntity): BooksUI {
            return BooksUI(
                bookEntity.id,
                bookEntity.title,
                bookEntity.image,
                bookEntity.subtitle,
            )
        }

        fun fromDto(bookDto: BooksDto.BookPreviewDto): BooksUI {
            return BooksUI(
                id = bookDto.id,
                title = bookDto.title,
                image = bookDto.image,
                subtitle = bookDto.subtitle,
            )
        }
    }
}
