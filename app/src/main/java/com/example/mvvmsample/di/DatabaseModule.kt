package com.example.mvvmsample.di

import android.content.Context
import androidx.room.Room
import com.example.mvvmsample.data.sample.local.SampleDatabase
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
        @ApplicationContext context: Context
    ): SampleDatabase =
        Room
            .databaseBuilder(
                context = context,
                klass = SampleDatabase::class.java,
                name = "sample_database"
            ).build()
}
