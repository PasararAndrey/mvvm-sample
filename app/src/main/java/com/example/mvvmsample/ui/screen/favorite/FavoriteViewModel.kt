package com.example.mvvmsample.ui.screen.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmsample.data.books.repository.BookRepository
import com.example.mvvmsample.model.BookModel
import com.example.mvvmsample.ui.screen.bookdetails.BookDetailsViewModel
import com.example.mvvmsample.ui.screen.favorite.model.FavoriteUI
import com.example.mvvmsample.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<FavoriteUIState> = MutableStateFlow(FavoriteUIState())
    val uiState: StateFlow<FavoriteUIState> = _uiState.asStateFlow()

    init {
        getFavoriteBooks()
    }

    private fun getFavoriteBooks() {
        viewModelScope.launch {
            bookRepository.getFavoriteBooks().flowOn(Dispatchers.IO).collect { requestResult ->
                _uiState.update {
                    toState(requestResult)
                }
            }
        }
    }

    private fun toState(result: RequestResult<List<BookModel>>): FavoriteUIState {
        return when (result) {
            is RequestResult.Success -> FavoriteUIState(
                checkNotNull(result.data).map { model -> FavoriteUI.fromModel(model) },
                false,
            )

            is RequestResult.InProgress -> {
                FavoriteUIState(isLoading = true)
            }

            is RequestResult.Error -> {
                Log.e(BookDetailsViewModel.TAG, result.error.stackTraceToString())
                FavoriteUIState()
            }
        }
    }
}
