package com.findyourbook.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.findyourbook.data.books.local.BooksDatabase
import com.findyourbook.data.books.local.entity.BookPreviewEntity
import com.findyourbook.data.books.remote.BooksRemoteMediator
import com.findyourbook.data.books.remote.BooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PagerModule::class],
)
object TestPagerModule {
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
