package com.example.mvvmsample.data.books.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmsample.data.books.local.entity.BooksRemoteKeysEntity

@Dao
interface BooksRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<BooksRemoteKeysEntity>)

    @Query("SELECT * FROM book_remote_keys WHERE book_id = :id")
    suspend fun getRemoteKeyByBookID(id: Long): BooksRemoteKeysEntity?

    @Query("DELETE FROM book_remote_keys")
    suspend fun clearRemoteKeys()
}
