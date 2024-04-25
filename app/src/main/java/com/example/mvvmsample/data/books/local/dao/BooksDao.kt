package com.example.mvvmsample.data.books.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsample.data.books.local.entity.BookEntity

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookEntity>)

    @Query("SElECT * FROM books ORDER BY created_at")
    fun getBooks(): PagingSource<Int, BookEntity>

    @Query("DELETE FROM books")
    suspend fun clearAll()
}
