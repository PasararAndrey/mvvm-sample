package com.example.mvvm_sample.ui.screen.favorite

import androidx.lifecycle.ViewModel
import com.example.mvvm_sample.data.sample.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val sampleRepository: SampleRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteUIState())
    val uiState: StateFlow<FavoriteUIState> = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(randomNumber = Random(199).nextInt())
        }
    }
}