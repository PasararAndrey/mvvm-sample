package com.example.mvvmsample.data.books.utils

import okhttp3.Interceptor
import okhttp3.Response

class BooksApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("api-key", apiKey).build()
        return chain.proceed(request.newBuilder().url(url).build())
    }
}
