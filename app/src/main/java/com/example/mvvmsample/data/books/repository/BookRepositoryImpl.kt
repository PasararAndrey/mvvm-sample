package com.example.mvvmsample.data.books.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.mvvmsample.data.books.local.BooksDatabase
import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.example.mvvmsample.data.books.local.entity.BookPreviewEntity
import com.example.mvvmsample.data.books.remote.BooksService
import com.example.mvvmsample.di.dispatchers.IoDispatcher
import com.example.mvvmsample.model.BookModel
import com.example.mvvmsample.utils.RequestResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val booksDatabase: BooksDatabase,
    private val booksService: BooksService,
    private val bookPager: Pager<Int, BookPreviewEntity>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : BookRepository {
    override fun getBooks(): Flow<PagingData<BookPreviewEntity>> = bookPager.flow.flowOn(ioDispatcher)

    override fun getBookById(id: Long): Flow<RequestResult<BookModel>> {
        return flow {
            emit(RequestResult.InProgress())
            val book = booksDatabase.booksDao.getById(id)
            if (book != null) {
                emit(RequestResult.Success(BookModel.fromEntity(book)))
            } else {
                val remoteBook = booksService.getBookById(id).getOrThrow()
                booksDatabase.booksDao.upsert(BookEntity.fromDto(remoteBook))
                emit(RequestResult.Success(BookModel.fromDto(remoteBook)))
            }
        }.catch { throwable ->
            emit(RequestResult.Error(error = throwable))
        }
    }

    override suspend fun updateFavorite(id: Long): BookModel {
        val book = booksDatabase.booksDao.getById(id).let { entity ->
            checkNotNull(entity).copy(isFavorite = !entity.isFavorite)
        }
        booksDatabase.booksDao.upsert(book)
        return BookModel.fromEntity(book)
    }

    override fun getFavoriteBooks(): Flow<RequestResult<List<BookModel>>> {
        return booksDatabase.booksDao.getFavorites()
            .map { entities ->
                RequestResult.Success(entities.map { entity -> BookModel.fromEntity(entity) })
            }
    }
}
