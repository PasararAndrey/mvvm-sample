package com.example.mvvmsample.fake

import com.example.mvvmsample.data.books.remote.BooksService
import com.example.mvvmsample.data.books.remote.model.BookDTO
import com.example.mvvmsample.data.books.remote.model.BookSortBy
import com.example.mvvmsample.data.books.remote.model.BooksDto

class FakeBooksService : BooksService {
    private val books = listOf(
        List(1000) {
            BooksDto.BookPreviewDto(it.toLong(), it.toString(), title = "Book title $it")
        },
    )

    override suspend fun searchBooks(
        query: String?,
        sortBy: BookSortBy?,
        offset: Int,
        booksAmount: Int,
    ): Result<BooksDto> {
        return Result.success(
            BooksDto(
                available = 10000,
                books = books.map {
                    it.subList(offset, offset + booksAmount)
                },
                number = DEFAULT_OFFSET,
                offset = offset,
            ),
        )
    }

    override suspend fun getBookById(id: Long): Result<BookDTO> {
        return Result.success(BookDTO(id.toInt(), identifiers = BookDTO.Identifiers(), title = "Book title $id"))
    }

    companion object {
        const val DEFAULT_OFFSET = 20
    }
}
