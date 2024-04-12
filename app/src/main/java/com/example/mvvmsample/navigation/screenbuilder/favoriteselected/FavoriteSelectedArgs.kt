package com.example.mvvmsample.navigation.screenbuilder.favoriteselected

import androidx.lifecycle.SavedStateHandle
import com.example.mvvmsample.navigation.utils.NavDestination

class FavoriteSelectedArgs(val favoriteId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(checkNotNull(savedStateHandle[NavDestination.FavoriteSelected.SOME_ARG]) as String)
}
