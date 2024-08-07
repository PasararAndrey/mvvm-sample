package com.findyourbook.ui.screen.bookdetails

import androidx.lifecycle.SavedStateHandle
import com.findyourbook.data.books.repository.FakeBookRepository
import com.findyourbook.navigation.destinations.NavDestinations
import com.findyourbook.ui.screen.bookdetails.model.BookDetailsUI
import com.findyourbook.utils.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
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
            fakeBookRepository.emitBookByIdState(RequestResult.Success(fakeBookRepository.books.first()))
            assertEquals(BookDetailsUI.fromModel(fakeBookRepository.books.first()), viewModel.uiState.value.book)
        }

    @Test
    fun `should be in loading state`() =
        runTest {
            fakeBookRepository.emitBookByIdState(RequestResult.InProgress())
            assertEquals(true, viewModel.uiState.value.isLoading)
        }
}
