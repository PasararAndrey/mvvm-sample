package com.findyourbook.ui.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.findyourbook.ui.screen.books.components.BookElement
import com.findyourbook.utils.LocalSemantics

@Composable
fun FavoriteScreen(
    uiState: FavoriteUIState,
    modifier: Modifier = Modifier,
    onNavigateToBookDetails: (id: Long) -> Unit,
) {
    val favorites = remember(uiState.favoriteBooks) {
        uiState.favoriteBooks
    }

    val semantics = LocalSemantics.current

    Box(modifier = modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                Modifier
                    .align(Alignment.Center)
                    .testTag(semantics.favoriteBooksLoadingIndicator),
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            ) {
                items(favorites) { book ->
                    with(book) {
                        BookElement(id.toLong(), title, subtitle, image, Modifier.fillMaxWidth()) {
                            onNavigateToBookDetails(id.toLong())
                        }
                    }
                }
            }
        }
    }
}
