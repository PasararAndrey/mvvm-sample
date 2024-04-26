package com.example.mvvmsample.data.books.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsample.data.books.local.entity.BookPreviewEntity

@Dao
interface BookPreviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookPreviewEntity>)

    @Query("SElECT * FROM books_preview ORDER BY created_at")
    fun getBooks(): PagingSource<Int, BookPreviewEntity>

    @Query("DELETE FROM books_preview")
    suspend fun clearAll()
}
