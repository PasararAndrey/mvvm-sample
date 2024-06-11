package com.example.mvvmsample.data.books.repository

import androidx.paging.PagingData
import com.example.mvvmsample.data.books.local.entity.BookPreviewEntity
import com.example.mvvmsample.model.BookModel
import com.example.mvvmsample.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBookRepository : BookRepository {
    private val _books: MutableList<BookModel> = List(1000) { bookId ->
        BookModel(id = bookId)
    }.toMutableList()
    val books = _books.toList()

    private var returnedRequestResult: RequestResult<Any> = RequestResult.Success("")

    val thrownException = Exception("Predicted test error occurred")

    fun setBookModelRequestResult(requestResult: RequestResult<Any>) {
        returnedRequestResult = requestResult
    }

    override fun getBooks(): Flow<PagingData<BookPreviewEntity>> =
        flow {
            emit(
                PagingData.from(
                    _books.map { model ->
                        with(model) {
                            BookPreviewEntity(
                                id = id.toLong(),
                                title = title ?: "",
                                image = image ?: "",
                                subtitle = subtitle,
                            )
                        }
                    },
                ),
            )
        }

    override fun getBookById(id: Long): Flow<RequestResult<BookModel>> =
        flow {
            emit(
                when (returnedRequestResult) {
                    is RequestResult.Success -> RequestResult.Success(books[id.toInt()])
                    is RequestResult.InProgress -> RequestResult.InProgress()
                    is RequestResult.Error -> RequestResult.Error(error = thrownException)
                },
            )
        }

    override suspend fun updateFavorite(id: Long): BookModel {
        _books[id.toInt()] = BookModel(id = id.toInt(), isFavorite = true)
        return _books[id.toInt()]
    }

    override fun getFavoriteBooks(): Flow<RequestResult<List<BookModel>>> =
        flow {
            emit(
                when (returnedRequestResult) {
                    is RequestResult.Success -> RequestResult.Success(_books.filter { model -> model.isFavorite })
                    is RequestResult.InProgress -> RequestResult.InProgress()
                    is RequestResult.Error -> RequestResult.Error(error = thrownException)
                },
            )
        }
}
