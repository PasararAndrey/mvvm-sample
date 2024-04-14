package com.example.mvvmsample.ui.screen.books

import androidx.lifecycle.ViewModel
import com.example.mvvmsample.data.sample.repository.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    val sampleRepository: SampleRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BooksUIState())
    val uiState: StateFlow<BooksUIState> = _uiState.asStateFlow()
}
