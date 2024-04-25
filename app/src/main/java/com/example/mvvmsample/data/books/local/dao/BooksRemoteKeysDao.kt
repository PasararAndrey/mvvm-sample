package com.example.mvvmsample.data.books.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsample.data.books.local.entity.BookRemoteKeyEntity

@Dao
interface BooksRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKeys: BookRemoteKeyEntity)

    @Query("SELECT * FROM book_remote_keys WHERE id = :id")
    suspend fun getByID(id: String): BookRemoteKeyEntity

    @Query("DELETE FROM book_remote_keys")
    suspend fun clear()
}
