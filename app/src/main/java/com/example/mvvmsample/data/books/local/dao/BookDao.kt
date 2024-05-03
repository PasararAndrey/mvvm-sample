package com.example.mvvmsample.data.books.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.mvvmsample.data.books.local.entity.BookEntity

@Dao
interface BookDao {
    @Upsert
    suspend fun upsert(book: BookEntity)

    @Query("SELECT * FROM books WHERE book_id=:id")
    suspend fun getById(id: Long): BookEntity?
}
