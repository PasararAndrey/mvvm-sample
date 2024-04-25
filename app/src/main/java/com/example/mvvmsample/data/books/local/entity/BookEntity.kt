package com.example.mvvmsample.data.books.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("books")
data class BookEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("book_id")
    val id: Long,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("image")
    val image: String,
    @ColumnInfo("subtitle")
    val subtitle: String?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
)
