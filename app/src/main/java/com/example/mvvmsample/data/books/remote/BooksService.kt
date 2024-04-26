package com.example.mvvmsample.data.books.remote

import androidx.annotation.IntRange
import androidx.annotation.Keep
import com.example.mvvmsample.data.books.remote.model.BookDTO
import com.example.mvvmsample.data.books.remote.model.BookSortBy
import com.example.mvvmsample.data.books.remote.model.BooksDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * [API DOCUMENTATION](https://bigbookapi.com/docs/)
 */
@Keep
interface BooksService {
    /**
     * API details [here](https://bigbookapi.com/docs/#Search-Books)
     */
    @GET("/search-books")
    suspend fun searchBooks(
        @Query("query") query: String? = null,
        @Query("sort") sortBy: BookSortBy? = null,
        @Query("offset") @IntRange(OFFSET_FROM.toLong(), OFFSET_TO.toLong()) offset: Int = OFFSET_FROM,
        @Query("number") @IntRange(NUMBER_FROM.toLong(), NUMBER_TO.toLong()) booksAmount: Int = DEFAULT_OFFSET,
    ): Result<BooksDto>

    @GET("/{$ID}")
    suspend fun getBookById(
        @Path(ID) id: Long,
    ): Result<BookDTO>

    companion object {
        const val OFFSET_FROM = 0
        const val OFFSET_TO = 1000
        const val NUMBER_FROM = 1
        const val NUMBER_TO = 100
        const val DEFAULT_OFFSET = 20

        const val ID = "id"
    }
}
