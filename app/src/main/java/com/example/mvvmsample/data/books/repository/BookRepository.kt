package com.example.mvvmsample.data.books.repository

import androidx.paging.PagingData
import com.example.mvvmsample.data.books.local.entity.BookPreviewEntity
import com.example.mvvmsample.model.BookModel
import com.example.mvvmsample.utils.RequestResult
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getSearchBookStream(): Flow<PagingData<BookPreviewEntity>>

    fun getBookById(id: Long): Flow<RequestResult<BookModel>>

    suspend fun updateFavorite(id: Long): BookModel
}
