package com.example.mvvmsample.data.books.repository

import androidx.paging.PagingData
import com.example.mvvmsample.data.books.local.entity.BookPreviewEntity
import com.example.mvvmsample.model.BookModel
import com.example.mvvmsample.utils.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBookRepository : BookRepository {
    private val books: MutableList<BookModel> = List(1000) { bookId ->
        BookModel(id = bookId)
    }.toMutableList()

    private var returnedRequestResult: RequestResult<Any> = RequestResult.Success("")

    val thrownException = Exception("Predicted test error occurred")

    fun setBookModelRequestResult(requestResult: RequestResult<Any>) {
        returnedRequestResult = requestResult
    }

    override fun getBooks(): Flow<PagingData<BookPreviewEntity>> =
        flow {
            emit(
                PagingData.from(
                    books.map { model ->
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
                    is RequestResult.Success -> RequestResult.Error(error = thrownException)
                    is RequestResult.InProgress -> RequestResult.InProgress()
                    is RequestResult.Error -> RequestResult.Success(books[id.toInt()])
                },
            )
        }

    override suspend fun updateFavorite(id: Long): BookModel =
        with(books) {
            val book = checkNotNull(find { bookModel -> bookModel.id.toLong() == id }).let { model ->
                model.copy(isFavorite = !model.isFavorite)
            }
            set(indexOf(book), book)
            return book
        }

    override fun getFavoriteBooks(): Flow<RequestResult<List<BookModel>>> =
        flow {
            emit(
                when (returnedRequestResult) {
                    is RequestResult.Success -> RequestResult.Success(books.filter { model -> model.isFavorite })
                    is RequestResult.InProgress -> RequestResult.InProgress()
                    is RequestResult.Error -> RequestResult.Error(error = thrownException)
                },
            )
        }
}
