package com.example.mvvmsample.data.books.repository

import androidx.paging.PagingData
import com.example.mvvmsample.data.books.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getSearchBookStream(): Flow<PagingData<BookEntity>>
}
