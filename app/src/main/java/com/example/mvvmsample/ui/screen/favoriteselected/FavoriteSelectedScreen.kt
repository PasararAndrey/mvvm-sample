package com.example.mvvmsample.ui.screen.favoriteselected

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
fun FavoriteSelectedScreen(
    uiState: FavoriteSelectedUIState,
    modifier: Modifier = Modifier,
    navigateBackWithArgument: (arg: String) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, Alignment.CenterHorizontally) {
        Text(text = "Received number ${uiState.receivedNumber}")
        Button(onClick = { navigateBackWithArgument("200") }) {
            Text(text = "Navigate back with argument ${uiState.sendNumber}")
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun FavoriteScreenPreview() {
    FavoriteSelectedScreen(FavoriteSelectedUIState("100", "200")) {}
}
