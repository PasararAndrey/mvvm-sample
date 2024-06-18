package com.example.mvvmsample.data.books.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmsample.data.books.local.converter.AuthorsConverters
import com.example.mvvmsample.data.books.local.dao.BookPreviewDao
import com.example.mvvmsample.data.books.local.dao.BooksDao
import com.example.mvvmsample.data.books.local.dao.BooksRemoteKeysDao
import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.example.mvvmsample.data.books.local.entity.BookPreviewEntity
import com.example.mvvmsample.data.books.local.entity.BookRemoteKeyEntity

@Database(
    entities = [BookPreviewEntity::class, BookRemoteKeyEntity::class, BookEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(AuthorsConverters::class)
abstract class BooksDatabase : RoomDatabase() {
    abstract val booksPreviewDao: BookPreviewDao
    abstract val remoteKeysDao: BooksRemoteKeysDao
    abstract val booksDao: BooksDao
}
