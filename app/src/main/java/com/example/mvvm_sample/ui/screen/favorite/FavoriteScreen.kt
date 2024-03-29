package com.example.mvvm_sample.ui.screen.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel = hiltViewModel()) {
    val state = favoriteViewModel.uiState.collectAsState()
    Text(text = state.value.randomNumber.toString())
}