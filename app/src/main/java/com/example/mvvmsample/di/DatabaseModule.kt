package com.example.mvvmsample.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmsample.BuildConfig
import com.example.mvvmsample.data.books.local.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideSampleDatabase(
        @ApplicationContext context: Context,
    ): BooksDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = BooksDatabase::class.java,
            name = BuildConfig.DB_NAME_BOOK,
        ).fallbackToDestructiveMigration().build()
    }
}
