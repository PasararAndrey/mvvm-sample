package com.example.mvvmsample.ui.screen.books

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.mvvmsample.R
import com.example.mvvmsample.ui.screen.books.model.BooksUI

@Suppress("UNUSED_PARAMETER")
@Composable
fun BooksScreen(
    modifier: Modifier = Modifier,
    onNavigateToBookDetails: (arg: String) -> Unit,
) {
    val viewModel = hiltViewModel<BooksViewModel>()

    val booksPagingItems = viewModel.getBooks.collectAsLazyPagingItems()

    Box(modifier = modifier.fillMaxSize()) {
        if (booksPagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(
                    count = booksPagingItems.itemCount,
                    key = booksPagingItems.itemKey { it.id },
                ) { index ->
                    val book: BooksUI? = booksPagingItems[index]
                    with(book) {
                        if (this != null) {
                            BookElement(id, title, subtitle, image, Modifier.fillMaxWidth())
                        }
                    }
                }

                item {
                    if (booksPagingItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun BookElement(
    id: Long,
    title: String,
    subtitle: String?,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(4.dp)
            .height(192.dp)
            .clickable {
                Log.d("Book element", "Book id is $id")
            },
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(192.dp),
        ) {
            val state = painter.state
            when (state) {
                AsyncImagePainter.State.Empty -> SubcomposeAsyncImageContent()
                is AsyncImagePainter.State.Error -> Icon(
                    painter = painterResource(id = R.drawable.image_error),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )

                is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = subtitle ?: "No description provided",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 4,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun BookElementPreview() {
    BookElement(
        1,
        "Title",
        null,
        "",
    )
}
