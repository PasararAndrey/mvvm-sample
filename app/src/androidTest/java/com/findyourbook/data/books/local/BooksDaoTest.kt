package com.findyourbook.data.books.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.findyourbook.data.books.local.dao.BooksDao
import com.findyourbook.data.books.local.entity.BookEntity
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import javax.inject.Inject

@HiltAndroidTest
@SmallTest
class BooksDaoTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: BooksDatabase
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
