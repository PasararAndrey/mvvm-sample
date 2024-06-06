package com.example.mvvmsample.ui.screen.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.mvvmsample.ui.screen.books.components.BookElement
import com.example.mvvmsample.ui.screen.books.model.BooksUI
import com.example.mvvmsample.utils.LocalSemantics

@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    onNavigateToBookDetails: (bookId: Long) -> Unit,
    uiState: BooksUIState,
) {
    val books = uiState.books.collectAsLazyPagingItems()
    val semanticsStrings = LocalSemantics.current

    Box(modifier = modifier.fillMaxSize()) {
        if (books.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                Modifier
                    .align(Alignment.Center)
                    .testTag(semanticsStrings.booksListLoadingIndicator),
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .semantics {
                        testTag = semanticsStrings.booksList
                    },
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
            ) {
                listContent(books, onNavigateToBookDetails)
                progressIndicator(books)
            }
        }
    }
}

private fun LazyListScope.listContent(
    books: LazyPagingItems<BooksUI>,
    onNavigateToBookDetails: (bookId: Long) -> Unit,
) {
    items(
        count = books.itemCount,
        key = books.itemKey { it.id },
    ) { index ->
        val book: BooksUI? = books[index]
        with(book) {
            if (this != null) {
                BookElement(id, title, subtitle, image, Modifier.fillMaxWidth()) {
                    onNavigateToBookDetails(id)
                }
            }
        }
    }
}

private fun LazyListScope.progressIndicator(books: LazyPagingItems<BooksUI>) {
    item {
        if (books.loadState.append is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
    }
}
