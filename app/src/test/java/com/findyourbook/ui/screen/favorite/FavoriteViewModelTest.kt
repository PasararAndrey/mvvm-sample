@file:OptIn(ExperimentalCoroutinesApi::class)

package com.findyourbook.ui.screen.favorite

import com.findyourbook.data.books.repository.FakeBookRepository
import com.findyourbook.ui.screen.favorite.model.FavoriteUI
import com.findyourbook.utils.RequestResult
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

class FavoriteViewModelTest {
    private lateinit var fakeBookRepository: FakeBookRepository
    private lateinit var viewModel: FavoriteViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        fakeBookRepository = FakeBookRepository()
        viewModel = FavoriteViewModel(fakeBookRepository, StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should be in loading state`() =
        runTest {
            fakeBookRepository.setBookModelRequestResult(RequestResult.InProgress())
            advanceUntilIdle()
            assertEquals(true, viewModel.uiState.value.isLoading)
        }

    @Test
    fun `should set default state on error`() =
        runTest {
            fakeBookRepository.setBookModelRequestResult(
                RequestResult.Error(error = fakeBookRepository.thrownException),
            )
            assertEquals(viewModel.uiState.value.isLoading, false)
            assertEquals(viewModel.uiState.value.favoriteBooks, emptyList<FavoriteUI>())
        }

    @Test
    fun `should get favorite books`() =
        runTest {
            viewModel.uiState.value.favoriteBooks.forEachIndexed { index, favoriteUI ->
                assertEquals(favoriteUI.id, index)
            }
        }
}
