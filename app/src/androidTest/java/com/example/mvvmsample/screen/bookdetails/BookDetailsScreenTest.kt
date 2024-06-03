package com.example.mvvmsample.screen.bookdetails

import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.mvvmsample.MainActivity
import com.example.mvvmsample.R
import com.example.mvvmsample.ui.screen.bookdetails.BookDetailsScreen
import com.example.mvvmsample.ui.screen.bookdetails.BookDetailsUIState
import com.example.mvvmsample.ui.screen.bookdetails.model.BookDetailsUI
import com.example.mvvmsample.ui.theme.ComposeEducationTheme
import com.example.mvvmsample.utils.LocalSemanticsStrings
import com.example.mvvmsample.utils.SemanticsStrings
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BookDetailsScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val semantics: SemanticsStrings = SemanticsStrings()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun shouldShowCorrectStateWhenInfoProvided() {
        val testBookDetailsUI = BookDetailsUI(
            bookId = "1",
            title = "Test Title",
            image = "https://example.com/image.jpg",
            description = "Test Description",
            authors = listOf("Author One", "Author Two"),
            rating = 4.5,
            isFavorite = false,
        )

        val uiState = BookDetailsUIState(book = testBookDetailsUI)

        composeRule.activity.setContent {
            ComposeEducationTheme {
                CompositionLocalProvider(LocalSemanticsStrings provides semantics) {
                    BookDetailsScreen(uiState = uiState)
                }
            }
        }
        composeRule.onNodeWithTag(semantics.bookDetailsTitle)
            .assertTextEquals(checkNotNull(testBookDetailsUI.title))
        composeRule.onNodeWithTag(semantics.bookDetailsAuthors)
            .assertTextEquals(checkNotNull(testBookDetailsUI.authors?.joinToString()))
        composeRule.onNodeWithTag(semantics.bookDetailsDescription)
            .assertTextEquals(checkNotNull(testBookDetailsUI.description))
    }

    @Test
    fun shouldUpdateFavoriteStateOnClick() {
        var favoriteClicked = false
        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemanticsStrings provides semantics) {
                BookDetailsScreen(uiState = BookDetailsUIState()) {
                    favoriteClicked = true
                }
            }
        }
        composeRule.onNodeWithTag(semantics.bookDetailsFavoriteIcon).performClick()
        assert(favoriteClicked)
    }

    @Test
    fun shouldShowDefaultStateWhenNoInfoProvided() {
        val book = BookDetailsUI(
            title = null,
            image = null,
            description = null,
            authors = null,
            rating = null,
            isFavorite = false,
        )
        val uiState = BookDetailsUIState(book)
        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemanticsStrings provides semantics) {
                BookDetailsScreen(
                    uiState = uiState,
                )
            }
        }
        composeRule.onNodeWithTag(semantics.bookDetailsTitle)
            .assertTextEquals(composeRule.activity.applicationContext.getString(R.string.no_title_provided))
        composeRule.onNodeWithTag(semantics.bookDetailsAuthors)
            .assertTextEquals(composeRule.activity.applicationContext.getString(R.string.no_information_about_authors))
        composeRule.onNodeWithTag(semantics.bookDetailsDescription)
            .assertTextEquals(composeRule.activity.applicationContext.getString(R.string.no_description_provided))
        composeRule.onNodeWithTag(semantics.bookDetailsRating)
            .assertIsNotDisplayed()
    }

    @Test
    fun shouldShowLoadingIndicator() {
        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemanticsStrings provides semantics) {
                BookDetailsScreen(uiState = BookDetailsUIState(isLoading = true))
            }
        }
        composeRule.onNodeWithTag(semantics.bookDetailsLoadingIndicator).assertIsDisplayed()
    }
}
