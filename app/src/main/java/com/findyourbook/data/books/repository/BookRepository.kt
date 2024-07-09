package com.findyourbook.data.books.repository

import androidx.paging.PagingData
import com.findyourbook.data.books.local.entity.BookPreviewEntity
import com.findyourbook.model.BookModel
import com.findyourbook.utils.RequestResult
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks(): Flow<PagingData<BookPreviewEntity>>

    fun getBookById(id: Long): Flow<RequestResult<BookModel>>

    suspend fun updateFavorite(id: Long): BookModel

    fun getFavoriteBooks(): Flow<RequestResult<List<BookModel>>>
}
