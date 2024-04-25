package com.example.mvvmsample.ui.screen.bookdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.mvvmsample.R
import com.example.mvvmsample.ui.screen.bookdetails.model.BookDetailsUI

@Composable
fun BookDetailsScreen(
    uiState: BookDetailsUIState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            ScreenContent(uiState)
        }
    }
}

@Composable
private fun ScreenContent(uiState: BookDetailsUIState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Spacer(modifier = Modifier.size(96.dp))

        SubcomposeAsyncImage(
            model = uiState.book.image,
            contentDescription = null,
            modifier = Modifier.size(192.dp),
        ) {
            val state = painter.state
            when (state) {
                is AsyncImagePainter.State.Empty -> SubcomposeAsyncImageContent()
                is AsyncImagePainter.State.Error -> Icon(
                    painter = painterResource(id = R.drawable.image_error),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )

                is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.book.title ?: stringResource(id = R.string.no_title_provided),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )

        Text(
            text = uiState.book.authors?.joinToString()
                ?: stringResource(R.string.no_information_about_authors),
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 4.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )

        uiState.book.rating?.let { rating ->
            Text(text = stringResource(R.string.rating, rating))
        }

        Text(
            text = uiState.book.description ?: stringResource(R.string.no_description_provided),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview(showBackground = true, name = "Bad case")
private fun BookDetailsBadCasePreview() {
    BookDetailsScreen(
        BookDetailsUIState(
            BookDetailsUI(),
            false,
        ),
    )
}

@Composable
@Preview(showBackground = true, name = "Good case")
private fun BookDetailsGoodCasePreview() {
    BookDetailsScreen(
        BookDetailsUIState(
            BookDetailsUI(
                "100",
                "Some title",
                null,
                "Some description",
                listOf("Vremenaya Peremennaya", " Nick V. Robert", "Anton Ne Anton"),
                0.9932,
            ),
            false,
        ),
    )
}
