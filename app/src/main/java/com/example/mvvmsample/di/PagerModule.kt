package com.example.mvvmsample.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.mvvmsample.data.books.local.BooksDatabase
import com.example.mvvmsample.data.books.local.entity.BookPreviewEntity
import com.example.mvvmsample.data.books.remote.BooksRemoteMediator
import com.example.mvvmsample.data.books.remote.BooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagerModule {
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBookPager(
        booksDatabase: BooksDatabase,
        booksService: BooksService,
    ): Pager<Int, BookPreviewEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = BooksService.DEFAULT_OFFSET,
                prefetchDistance = 5,
                initialLoadSize = BooksService.DEFAULT_OFFSET,
            ),
            pagingSourceFactory = { booksDatabase.booksPreviewDao.getBooks() },
            remoteMediator = BooksRemoteMediator(booksDatabase, booksService),
        )
    }
}
