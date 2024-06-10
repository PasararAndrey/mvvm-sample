package com.example.mvvmsample.data.books.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.mvvmsample.data.books.local.dao.BooksDao
import com.example.mvvmsample.data.books.local.entity.BookEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
class BooksDaoTest {
    private lateinit var database: BooksDatabase
    private lateinit var dao: BooksDao

    private val bookId = 100
    private val book = BookEntity(bookId)

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BooksDatabase::class.java,
        ).allowMainThreadQueries().build()
        dao = database.booksDao
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun shouldInsertBook() =
        runTest {
            dao.upsert(book)
            val bookEntity: BookEntity = checkNotNull(dao.getById(bookId.toLong()))
            assertThat(bookEntity.id).isEqualTo(bookId)
        }

    @Test
    fun shouldAddBookToFavorites() =
        runTest {
            dao.upsert(book.copy(isFavorite = true))
            val favorites = dao.getFavorites().first()
            assertThat(favorites.first().isFavorite).isTrue()
        }

    @Test
    fun shouldUpdateBook() =
        runTest {
            val title = "book title"
            dao.upsert(book)
            dao.upsert(book.copy(title = title))
            assertThat(dao.getById(bookId.toLong())?.title).isEqualTo(title)
        }
}
