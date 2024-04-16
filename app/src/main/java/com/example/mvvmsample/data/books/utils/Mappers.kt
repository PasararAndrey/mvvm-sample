package com.example.mvvmsample.data.books.utils

import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.example.mvvmsample.data.books.remote.model.BookDto

fun BookDto.toEntity(): BookEntity = BookEntity(id = id, title = title, image = image, subtitle = subtitle)

fun List<BookDto>.toEntityList(): List<BookEntity> = map { it.toEntity() }
