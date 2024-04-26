package com.example.mvvmsample.data.books.local.converter

import androidx.room.TypeConverter
import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AuthorsConverters {
    @TypeConverter
    fun fromString(str: String?): List<BookEntity.Author>? {
        val typeToken = object : TypeToken<List<BookEntity.Author>?>() {}.type
        return Gson().fromJson(str, typeToken)
    }

    @TypeConverter
    fun toString(authors: List<BookEntity.Author>?): String {
        return Gson().toJson(authors)
    }
}
