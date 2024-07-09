package com.findyourbook.di

import android.content.Context
import androidx.room.Room
import com.findyourbook.data.books.local.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class],
)
@Module
class TestDatabaseModule {
    @Provides
    @Singleton
    fun provideSampleDatabase(
        @ApplicationContext context: Context,
    ): BooksDatabase {
        return Room.inMemoryDatabaseBuilder(
            context = context,
            klass = BooksDatabase::class.java,
        ).fallbackToDestructiveMigration().build()
    }
}
