package com.example.mvvmsample.data.books.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsample.data.books.local.entity.BookEntity

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookEntity)

    @Query("SELECT * FROM books WHERE book_id=:id")
    suspend fun getById(id: Long): BookEntity?
}
