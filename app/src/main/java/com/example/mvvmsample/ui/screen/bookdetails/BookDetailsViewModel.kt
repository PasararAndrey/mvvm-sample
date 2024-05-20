package com.example.mvvmsample.ui.screen.bookdetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.data.books.repository.BookRepository
import com.example.mvvmsample.model.BookModel
import com.example.mvvmsample.navigation.navgraphbuilder.bookdetails.BookDetailsArgs
import com.example.mvvmsample.ui.screen.bookdetails.model.BookDetailsUI
import com.example.mvvmsample.utils.RequestResult
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
        Log.d(TAG, args.bookId)
        bookRepository.getBookById(args.bookId.toLong()).onEach { result ->
            Log.d(TAG, result.toString())
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
                Log.e(TAG, result.error.stackTraceToString())
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
