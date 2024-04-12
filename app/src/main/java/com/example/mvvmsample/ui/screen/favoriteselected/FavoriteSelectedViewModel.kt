package com.example.mvvmsample.ui.screen.favoriteselected

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mvvmsample.navigation.screenbuilder.favoriteselected.FavoriteSelectedArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteSelectedViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args = FavoriteSelectedArgs(savedStateHandle)
    private val _uiState = MutableStateFlow(FavoriteSelectedUIState(args.favoriteId))
    val uiState: StateFlow<FavoriteSelectedUIState> = _uiState.asStateFlow()
}
