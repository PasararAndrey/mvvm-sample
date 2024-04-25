package com.example.mvvmsample.data.books.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class BooksApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: Request = chain.request().newBuilder().addHeader("x-api-key", apiKey).build()
        return chain.proceed(url)
    }
}
