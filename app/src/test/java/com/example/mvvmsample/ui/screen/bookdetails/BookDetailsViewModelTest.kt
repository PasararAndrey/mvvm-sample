package com.example.mvvmsample.ui.screen.bookdetails

import androidx.lifecycle.SavedStateHandle
import com.example.mvvmsample.data.books.repository.FakeBookRepository
import com.example.mvvmsample.navigation.destinations.NavDestinations
import com.example.mvvmsample.ui.screen.bookdetails.model.BookDetailsUI
import com.example.mvvmsample.utils.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookDetailsViewModelTest {
    private lateinit var fakeBookRepository: FakeBookRepository
    private lateinit var viewModel: BookDetailsViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        fakeBookRepository = FakeBookRepository()
        viewModel =
            BookDetailsViewModel(
                SavedStateHandle(mapOf(NavDestinations.BookDetails.BOOK_ID to "0")),
                fakeBookRepository,
            )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should set default state on error`() =
        runTest {
            fakeBookRepository.setBookModelRequestResult(
                RequestResult.Error(error = fakeBookRepository.thrownException),
            )
            assertEquals(false, viewModel.uiState.value.isLoading)
        }

    @Test
    fun `should get book details`() =
        runTest {
            advanceUntilIdle()
            assertEquals(BookDetailsUI.fromModel(fakeBookRepository.books.first()), viewModel.uiState.value.book)
        }

    @Test
    fun `should update favorite state`() =
        runTest {
            advanceUntilIdle()
            val isFavorite = viewModel.uiState.value.book.isFavorite
            viewModel.onFavoriteChange()
            advanceUntilIdle()
            assertEquals(!isFavorite, viewModel.uiState.value.book.isFavorite)
        }
}
