package com.example.mvvmsample.data.books.repository

import androidx.paging.PagingData
import com.example.mvvmsample.data.books.local.entity.BookPreviewEntity
import com.example.mvvmsample.model.BookModel
import com.example.mvvmsample.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class FakeBookRepository : BookRepository {
    private val _books: MutableList<BookModel> = List(1000) { bookId ->
        BookModel(id = bookId)
    }.toMutableList()
    val books = _books.toList()

    private var returnedRequestResult: RequestResult<Any> = RequestResult.Success("")

    val thrownException = Exception("Predicted test error occurred")

    private val getBookByIdFlow = MutableSharedFlow<RequestResult<BookModel>>()

    suspend fun emitBookByIdState(requestResult: RequestResult<BookModel>) = getBookByIdFlow.emit(requestResult)

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

    override fun getBookById(id: Long): Flow<RequestResult<BookModel>> = getBookByIdFlow

    override suspend fun updateFavorite(id: Long): BookModel {
        val newBook = BookModel(id = id.toInt(), isFavorite = true)
        _books[id.toInt()] = BookModel(id = id.toInt(), isFavorite = true)
        emitBookByIdState(RequestResult.Success(newBook))
        return newBook
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
