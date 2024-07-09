package com.findyourbook.di

import android.content.Context
import androidx.room.Room
import com.findyourbook.BuildConfig
import com.findyourbook.data.books.local.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
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
