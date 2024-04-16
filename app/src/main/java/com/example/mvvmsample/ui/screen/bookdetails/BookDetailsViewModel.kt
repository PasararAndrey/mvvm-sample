package com.example.mvvmsample.ui.screen.bookdetails

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(BookDetailsUIState())
    val uiState: StateFlow<BookDetailsUIState> = _uiState.asStateFlow()
}
