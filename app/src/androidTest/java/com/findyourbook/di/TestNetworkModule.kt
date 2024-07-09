package com.findyourbook.di

import com.findyourbook.data.books.remote.BooksService
import com.findyourbook.di.network.NetworkModule
import com.findyourbook.fake.FakeBooksService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [NetworkModule::class])
class TestNetworkModule {
    @Provides
    @Singleton
    fun provideSampleService(): BooksService = FakeBooksService()
}
