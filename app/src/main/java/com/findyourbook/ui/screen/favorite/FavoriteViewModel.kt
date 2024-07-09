package com.findyourbook.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findyourbook.data.books.repository.BookRepository
import com.findyourbook.di.dispatchers.IoDispatcher
import com.findyourbook.model.BookModel
import com.findyourbook.ui.screen.favorite.model.FavoriteUI
import com.findyourbook.utils.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _uiState: MutableStateFlow<FavoriteUIState> = MutableStateFlow(FavoriteUIState())
    val uiState: StateFlow<FavoriteUIState> = _uiState.asStateFlow()

    init {
        getFavoriteBooks()
    }

    private fun getFavoriteBooks() {
        viewModelScope.launch {
            bookRepository.getFavoriteBooks().flowOn(dispatcher).collect { requestResult ->
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
                FavoriteUIState()
            }
        }
    }
}
