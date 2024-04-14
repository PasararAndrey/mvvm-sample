package com.example.mvvmsample.ui.screen.books

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Suppress("UNUSED_PARAMETER")
@Composable
fun BooksScreen(
    uiState: BooksUIState,
    modifier: Modifier = Modifier,
    onNavigateToBookDetails: (arg: String) -> Unit,
) {
    Box(modifier = modifier) {
        Text(text = "books")
    }
}

@Composable
@Preview(showBackground = true)
private fun BooksScreenPreview() {
    BooksScreen(BooksUIState()) { }
}
