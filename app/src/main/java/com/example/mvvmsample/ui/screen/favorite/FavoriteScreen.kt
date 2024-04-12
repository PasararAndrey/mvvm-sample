package com.example.mvvmsample.ui.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteScreen(
    uiState: FavoriteUIState,
    modifier: Modifier = Modifier,
    onNavigateToSelectedFavorite: (arg: String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { onNavigateToSelectedFavorite(uiState.sendNumber) }) {
            Text(text = "Send to selected favorite number ${uiState.sendNumber}")
        }

        uiState.receivedNumber?.let {
            Text(text = "Received number $it")
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun FavoriteScreenPreview() {
    FavoriteScreen(FavoriteUIState("1000", "200")) { }
}
