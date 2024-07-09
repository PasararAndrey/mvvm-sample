package com.findyourbook.ui.screen.favorite

import androidx.compose.runtime.Immutable
import com.findyourbook.ui.screen.favorite.model.FavoriteUI

@Immutable
data class FavoriteUIState(
    val favoriteBooks: List<FavoriteUI> = emptyList(),
    val isLoading: Boolean = false,
)
