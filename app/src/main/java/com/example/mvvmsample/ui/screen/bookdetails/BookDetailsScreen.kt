package com.example.mvvmsample.ui.screen.bookdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Suppress("UNUSED_PARAMETER")
@Composable
fun BookDetailsScreen(
    uiState: BookDetailsUIState,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(text = "Books Details")
    }
}

@Composable
@Preview(showBackground = true)
private fun BookDetailsPreview() {
    BookDetailsScreen(BookDetailsUIState("100"))
}
