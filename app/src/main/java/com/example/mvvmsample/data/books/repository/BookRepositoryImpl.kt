package com.example.mvvmsample.data.books.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.mvvmsample.data.books.local.BooksDatabase
import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.example.mvvmsample.data.books.remote.BooksService
import com.example.mvvmsample.data.books.remote.model.BookByIdDTO
import com.example.mvvmsample.di.dispatchers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@Suppress("UnusedPrivateProperty")
class BookRepositoryImpl @Inject constructor(
    private val booksDatabase: BooksDatabase,
    private val booksService: BooksService,
    private val bookPager: Pager<Int, BookEntity>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BookRepository {
    override fun getSearchBookStream(): Flow<PagingData<BookEntity>> = bookPager.flow.flowOn(ioDispatcher)

    override suspend fun getBookById(id: Long): Result<BookByIdDTO> {
        return booksService.getBookById(id)
    }
}
