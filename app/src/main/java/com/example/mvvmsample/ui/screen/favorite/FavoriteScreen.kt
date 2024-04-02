package com.example.mvvmsample.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val state = favoriteViewModel.uiState.collectAsState()
    FavoriteScreen(state.value.randomNumber.toString(), modifier = modifier)
}

@Composable
private fun FavoriteScreen(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text)
    }
}

@Composable
@Preview(showBackground = true)
private fun FavoriteScreenPreview() {
    FavoriteScreen("12345")
}
