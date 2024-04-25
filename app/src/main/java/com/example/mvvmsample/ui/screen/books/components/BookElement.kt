@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mvvmsample.ui.screen.books.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.mvvmsample.R

@Composable
fun BookElement(
    title: String,
    subtitle: String?,
    imageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onClick() },
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(IntrinsicSize.Max)
                .fillMaxWidth(),
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .height(192.dp),
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

            Spacer(modifier = Modifier.width(8.dp))

            @Suppress("MagicNumber")
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(32.dp))
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
}

@Composable
@Preview(showBackground = true)
private fun BookElementPreview() {
    BookElement(
        "Title",
        null,
        "",
    ) { }
}
