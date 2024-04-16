package com.example.mvvmsample.data.books.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.mvvmsample.data.books.local.BooksDatabase
import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.example.mvvmsample.data.books.local.entity.BooksRemoteKeysEntity
import com.example.mvvmsample.data.books.remote.model.BookDto
import com.example.mvvmsample.data.books.remote.model.BooksDto
import com.example.mvvmsample.data.books.utils.toEntityList
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class BooksRemoteMediator @Inject constructor(
    private val booksDb: BooksDatabase,
    private val booksService: BooksService,
) : RemoteMediator<Int, BookEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BookEntity>,
    ): MediatorResult {
        val offset: Int = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextOffset = remoteKeys?.nextOffset
                nextOffset ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
        }

        return try {
            val result: Result<BooksDto> = booksService.searchBooks(
                offset = offset,
                booksAmount = state.config.pageSize,
            )
            if (result.isSuccess) {
                val booksDto = result.getOrThrow()
                val endOfPaginationReached = booksDto.offset + booksDto.number > BooksService.OFFSET_TO

                booksDb.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        booksDb.booksDao.clearAll()
                        booksDb.remoteKeysDao.clearRemoteKeys()
                    }

                    val prevOffset = if (offset > 1) booksDto.offset - booksDto.number else null
                    val nextOffset = if (endOfPaginationReached) null else booksDto.offset + booksDto.number

                    val remoteKeys: List<BooksRemoteKeysEntity> = booksDto.books.map { nestedList ->
                        BooksRemoteKeysEntity(nestedList.first().id, prevOffset, offset, nextOffset)
                    }

                    booksDb.remoteKeysDao.insertAll(remoteKeys)
                    booksDb.booksDao.insertAll(
                        booksDto.books.map { nestedList: List<BookDto> ->
                            nestedList.first()
                        }.toEntityList(),
                    )
                }

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

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, BookEntity>): BooksRemoteKeysEntity? {
        val page = state.pages.lastOrNull { page ->
            page.data.isNotEmpty()
        }
        val remoteKey = page?.data?.lastOrNull()?.let { book ->
            booksDb.remoteKeysDao.getRemoteKeyByBookID(book.id)
        }
        return remoteKey
    }
}
