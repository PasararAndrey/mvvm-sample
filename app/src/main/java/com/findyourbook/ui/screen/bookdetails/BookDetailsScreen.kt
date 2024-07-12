package com.findyourbook.ui.screen.bookdetails

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.findyourbook.R
import com.findyourbook.ui.screen.bookdetails.model.BookDetailsUI
import com.findyourbook.utils.LocalSemantics
import com.findyourbook.utils.SemanticsStrings
import com.findyourbook.utils.imageVector

@Composable
fun BookDetailsScreen(
    uiState: BookDetailsUIState,
    modifier: Modifier = Modifier,
    onFavorite: () -> Unit = {},
) {
    val semantics = LocalSemantics.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        ScreenContent(uiState.book, semantics, onFavorite)
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag(semantics.bookDetailsLoadingIndicator),
            )
        }
    }
}

@Composable
private fun ScreenContent(
    bookDetailsState: BookDetailsUI,
    semantics: SemanticsStrings,
    onFavorite: () -> Unit,
) {
    val favoriteIcon: ImageVector = rememberFavoriteIcon(bookDetailsState.isFavorite)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Spacer(modifier = Modifier.size(96.dp))
        BookImage(bookDetailsState.image, semantics.bookDetailsImage, semantics.bookDetailsImageError)
        Title(bookDetailsState.title, semantics.bookDetailsTitle)
        Authors(bookDetailsState.authors, semantics.bookDetailsAuthors)
        Rating(bookDetailsState.rating, semantics.bookDetailsRating)
        Description(bookDetailsState.description, semantics.bookDetailsDescription)
        FavoriteIcon(favoriteIcon, semantics.bookDetailsFavoriteIcon, onFavorite)
    }
}

@Composable
private fun BookImage(
    image: String?,
    semanticImage: String,
    semanticError: String,
) {
    SubcomposeAsyncImage(
        model = image,
        contentDescription = semanticImage,
        modifier = Modifier.size(192.dp),
    ) {
        val state = painter.state
        when (state) {
            is AsyncImagePainter.State.Empty -> SubcomposeAsyncImageContent()
            is AsyncImagePainter.State.Error -> Icon(
                painter = painterResource(id = R.drawable.image_error),
                contentDescription = semanticError,
                tint = MaterialTheme.colorScheme.onBackground,
            )

            is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
        }
    }
}

@Composable
private fun Title(
    title: String?,
    semanticTitle: String,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                testTag = semanticTitle
                contentDescription = semanticTitle
            },
        text = title ?: stringResource(id = R.string.no_title_provided),
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun Authors(
    authors: List<String>?,
    semanticAuthors: String,
) {
    Text(
        text = authors?.joinToString() ?: stringResource(R.string.no_information_about_authors),
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 4.dp)
            .semantics {
                testTag = semanticAuthors
                contentDescription = semanticAuthors
            },
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun Rating(
    rating: Double?,
    semanticRating: String,
) {
    if (rating != null) {
        Text(
            text = stringResource(R.string.rating, rating),
            modifier = Modifier.testTag(semanticRating),
        )
    }
}

@Composable
private fun Description(
    description: String?,
    semanticDescription: String,
) {
    Text(
        text = description ?: stringResource(R.string.no_description_provided),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
        modifier = Modifier.testTag(semanticDescription),
    )
}

@Composable
private fun FavoriteIcon(
    favoriteIcon: ImageVector,
    semanticFavoriteIcon: String,
    onFavorite: () -> Unit,
) {
    IconButton(
        onClick = onFavorite,
        Modifier.semantics {
            testTag = semanticFavoriteIcon
            contentDescription = semanticFavoriteIcon
        },
    ) {
        Icon(
            favoriteIcon,
            null,
            modifier = Modifier.semantics {
                imageVector = favoriteIcon
            },
        )
    }
}

@Composable
private fun rememberFavoriteIcon(isFavorite: Boolean) =
    remember(isFavorite) {
        if (isFavorite) {
            Icons.Default.Favorite
        } else {
            Icons.Default.FavoriteBorder
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
    ) {}
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
    ) {}
}
