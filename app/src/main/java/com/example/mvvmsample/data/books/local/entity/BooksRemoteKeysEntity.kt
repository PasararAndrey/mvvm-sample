package com.example.mvvmsample.data.books.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("book_remote_keys")
data class BooksRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "book_id")
    val bookId: Long,
    val prevOffset: Int?,
    val currentOffset: Int,
    val nextOffset: Int?,
)
