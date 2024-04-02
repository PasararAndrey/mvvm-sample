package com.example.mvvmsample.ui.screen.favorite

import androidx.lifecycle.ViewModel
import com.example.mvvmsample.data.sample.repository.SampleRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class FavoriteViewModel
@Inject
constructor(
    val sampleRepository: SampleRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteUIState())
    val uiState: StateFlow<FavoriteUIState> = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(randomNumber = Random(199).nextInt())
        }
    }
}
