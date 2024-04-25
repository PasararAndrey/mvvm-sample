package com.example.mvvmsample.data.books.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.mvvmsample.data.books.local.BooksDatabase
import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.example.mvvmsample.data.books.local.entity.BookRemoteKeyEntity
import com.example.mvvmsample.data.books.remote.model.BooksDto
import com.example.mvvmsample.data.books.utils.toEntityList
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BooksRemoteMediator @Inject constructor(
    private val database: BooksDatabase,
    private val service: BooksService,
) : RemoteMediator<Int, BookEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BookEntity>,
    ): MediatorResult {
        val offset: Int = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val remoteKey = database.remoteKeysDao.getByID(BOOK_ID)
                remoteKey.nextOffset ?: return MediatorResult.Success(endOfPaginationReached = true)
            }
        }

        return try {
            val result = service.searchBooks(offset = offset)
            if (result.isSuccess) {
                val booksDto = result.getOrThrow()
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        database.booksDao.clearAll()
                        database.remoteKeysDao.clear()
                    }

                    database.remoteKeysDao.insertOrReplace(
                        BookRemoteKeyEntity(
                            BOOK_ID,
                            (booksDto.offset + booksDto.number),
                        ),
                    )
                    val flatten: List<BooksDto.BookDto> = booksDto.books.flatten()
                    database.booksDao.insertAll(flatten.toEntityList())
                }
                val endOfPaginationReached = booksDto.offset + booksDto.number >= BooksService.OFFSET_TO
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } else {
                throw result.exceptionOrNull() ?: IOException("Unknown error")
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    companion object {
        private const val BOOK_ID = "book_id"
    }
}
