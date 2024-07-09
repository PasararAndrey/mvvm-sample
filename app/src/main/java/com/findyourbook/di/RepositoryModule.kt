package com.findyourbook.di

import com.findyourbook.data.books.repository.BookRepository
import com.findyourbook.data.books.repository.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(sampleRepositoryImpl: BookRepositoryImpl): BookRepository
}
