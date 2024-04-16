package com.example.mvvmsample.di.network

import com.example.mvvmsample.BuildConfig
import com.example.mvvmsample.data.books.remote.BooksService
import com.example.mvvmsample.data.books.utils.BooksApiKeyInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun provideOkHttpClient(
        @InterceptorDebugLogging loggingInterceptor: Interceptor,
        @InterceptorBooksApiKey apiKeyInterceptor: Interceptor,
    ): OkHttpClient =
        if (BuildConfig.DEBUG) {
            OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(apiKeyInterceptor)
                .build()
        } else {
            OkHttpClient
                .Builder()
                .addInterceptor(apiKeyInterceptor)
                .build()
        }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .build()

    @Provides
    fun provideSampleService(retrofit: Retrofit): BooksService = retrofit.create(BooksService::class.java)

    @InterceptorDebugLogging
    @Provides
    fun provideDebugHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @InterceptorBooksApiKey
    @Provides
    fun provideBooksApiKeyHttpInterceptor(): Interceptor {
        return BooksApiKeyInterceptor(BuildConfig.API_KEY)
    }
}
