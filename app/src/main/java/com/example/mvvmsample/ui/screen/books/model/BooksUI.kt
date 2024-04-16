package com.example.mvvmsample.ui.screen.books.model

import com.example.mvvmsample.data.books.local.entity.BookEntity

data class BooksUI(
    val id: Long,
    val title: String,
    val image: String,
    val subtitle: String?,
) {
    companion object {
        fun fromEntity(bookEntity: BookEntity): BooksUI {
            return BooksUI(
                bookEntity.id,
                bookEntity.title,
                bookEntity.image,
                bookEntity.subtitle,
            )
        }
    }
}
