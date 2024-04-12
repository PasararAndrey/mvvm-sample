package com.example.mvvmsample.ui.screen.favorite

import androidx.lifecycle.ViewModel
import com.example.mvvmsample.data.sample.repository.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val sampleRepository: SampleRepository,
) : ViewModel() {
    fun receiveNumber(number: String) {
        _uiState.update { it.copy(receivedNumber = number) }
    }

    private val _uiState = MutableStateFlow(FavoriteUIState())
    val uiState: StateFlow<FavoriteUIState> = _uiState.asStateFlow()
}
