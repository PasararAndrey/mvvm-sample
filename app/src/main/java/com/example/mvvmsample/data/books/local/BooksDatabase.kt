package com.example.mvvmsample.data.books.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmsample.data.books.local.dao.BooksDao
import com.example.mvvmsample.data.books.local.dao.BooksRemoteKeysDao
import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.example.mvvmsample.data.books.local.entity.BookRemoteKeyEntity

@Database(
    entities = [BookEntity::class, BookRemoteKeyEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class BooksDatabase : RoomDatabase() {
    abstract val booksDao: BooksDao
    abstract val remoteKeysDao: BooksRemoteKeysDao
}
