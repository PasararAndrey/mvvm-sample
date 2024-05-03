package com.example.mvvmsample.ui.screen.favorite

import com.example.mvvmsample.ui.screen.favorite.model.FavoriteUI

data class FavoriteUIState(
    val favoriteBooks: List<FavoriteUI> = emptyList(),
    val isLoading: Boolean = false,
)
