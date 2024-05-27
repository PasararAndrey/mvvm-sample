package com.example.mvvmsample.di

import com.example.mvvmsample.data.books.remote.BooksService
import com.example.mvvmsample.di.network.NetworkModule
import com.example.mvvmsample.fake.FakeBooksService
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
