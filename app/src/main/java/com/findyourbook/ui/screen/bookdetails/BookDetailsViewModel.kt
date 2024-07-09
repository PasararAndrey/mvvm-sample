package com.findyourbook.ui.screen.bookdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findyourbook.data.books.repository.BookRepository
import com.findyourbook.model.BookModel
import com.findyourbook.navigation.navgraphbuilder.bookdetails.BookDetailsArgs
import com.findyourbook.ui.screen.bookdetails.model.BookDetailsUI
import com.findyourbook.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val args = BookDetailsArgs(savedStateHandle)
    private val _uiState: MutableStateFlow<BookDetailsUIState> = MutableStateFlow(BookDetailsUIState())
    val uiState: StateFlow<BookDetailsUIState> = _uiState

    init {
        bookRepository.getBookById(args.bookId.toLong()).onEach { result ->
            _uiState.update {
                toState(result)
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            BookDetailsUIState(),
        )
    }

    private fun toState(result: RequestResult<BookModel>): BookDetailsUIState {
        return when (result) {
            is RequestResult.Success -> BookDetailsUIState(
                book = BookDetailsUI.fromModel(checkNotNull(result.data)),
                isLoading = false,
            )

            is RequestResult.InProgress -> {
                BookDetailsUIState(isLoading = true)
            }

            is RequestResult.Error -> {
                BookDetailsUIState()
            }
        }
    }

    fun onFavoriteChange() {
        viewModelScope.launch {
            val updatedBook = bookRepository.updateFavorite(uiState.value.book.bookId.toLong())
            _uiState.update { BookDetailsUIState(BookDetailsUI.fromModel(updatedBook)) }
        }
    }

    companion object {
        const val TAG = "BookDetailsViewModel"
    }
}
