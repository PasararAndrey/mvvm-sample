package com.example.mvvmsample.ui.screen.favorite

import androidx.compose.runtime.Immutable
import com.example.mvvmsample.ui.screen.favorite.model.FavoriteUI

@Immutable
data class FavoriteUIState(
    val favoriteBooks: List<FavoriteUI> = emptyList(),
    val isLoading: Boolean = false,
)
