package com.example.mvvmsample.ui.screen.bookdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.data.books.repository.BookRepository
import com.example.mvvmsample.navigation.navgraphbuilder.bookdetails.BookDetailsArgs
import com.example.mvvmsample.ui.screen.bookdetails.model.BookDetailsUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val args = BookDetailsArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(BookDetailsUIState(book = BookDetailsUI(bookId = args.bookId)))
    val uiState: StateFlow<BookDetailsUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            bookRepository.getBookById(args.bookId.toLong())
                .onSuccess { book ->
                    _uiState.value = BookDetailsUIState(
                        book = BookDetailsUI.fromDto(book),
                        isLoading = false,
                    )
                }.onFailure { e ->
                    Log.e(TAG, e.stackTraceToString())
                    _uiState.update { it.copy(isLoading = false) }
                }
        }
    }

    companion object {
        const val TAG = "BookDetailsViewModel"
    }
}
