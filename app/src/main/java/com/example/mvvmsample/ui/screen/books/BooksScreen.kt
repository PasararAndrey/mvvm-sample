package com.example.mvvmsample.ui.screen.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.mvvmsample.ui.screen.books.components.BookElement
import com.example.mvvmsample.ui.screen.books.model.BooksUI

@Suppress("UNUSED_PARAMETER")
@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    onNavigateToBookDetails: (arg: String) -> Unit,
    uiState: BooksUIState,
) {
    val books = uiState.books.collectAsLazyPagingItems()

    Box(modifier = modifier.fillMaxSize()) {
        if (books.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    count = books.itemCount,
                    key = books.itemKey { it.id },
                ) { index ->
                    val book: BooksUI? = books[index]
                    with(book) {
                        if (this != null) {
                            BookElement(title, subtitle, image, Modifier.fillMaxWidth())
                        }
                    }
                }

                item {
                    if (books.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
