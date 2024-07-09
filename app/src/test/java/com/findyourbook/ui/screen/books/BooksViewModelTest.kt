package com.findyourbook.ui.screen.books

import androidx.paging.testing.asSnapshot
import com.findyourbook.data.books.repository.FakeBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BooksViewModelTest {
    private lateinit var fakeBookRepository: FakeBookRepository
    private lateinit var viewModel: BooksViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        fakeBookRepository = FakeBookRepository()
        viewModel = BooksViewModel(fakeBookRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun shouldLoadBooks() =
        runTest {
            val itemsAmount = 50
            backgroundScope.launch(UnconfinedTestDispatcher()) {
                val items = viewModel.uiState.value.books.asSnapshot {
                    scrollTo(itemsAmount)
                }
                assertEquals(fakeBookRepository.books.take(itemsAmount), items)
            }
        }
}
