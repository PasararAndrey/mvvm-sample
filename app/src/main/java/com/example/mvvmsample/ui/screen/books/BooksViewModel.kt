package com.example.mvvmsample.ui.screen.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.mvvmsample.data.books.repository.BookRepository
import com.example.mvvmsample.ui.screen.books.model.BooksUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BooksUIState())
    val uiState: StateFlow<BooksUIState> = _uiState.asStateFlow()

    init {
        _uiState.update { booksUIState ->
            booksUIState.copy(
                books = bookRepository.getSearchBookStream().map { pagingData ->
                    pagingData.map { bookEntity ->
                        BooksUI.fromEntity(bookEntity)
                    }
                }.cachedIn(viewModelScope),
            )
        }
    }
}
