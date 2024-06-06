package com.example.mvvmsample.screen.favorite

import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.mvvmsample.MainActivity
import com.example.mvvmsample.ui.screen.favorite.FavoriteScreen
import com.example.mvvmsample.ui.screen.favorite.FavoriteUIState
import com.example.mvvmsample.ui.screen.favorite.model.FavoriteUI
import com.example.mvvmsample.utils.LocalSemantics
import com.example.mvvmsample.utils.SemanticsStrings
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class FavoriteScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private val testFavorites = listOf(
        FavoriteUI(id = 1, title = "Favorite Book 1", subtitle = "Subtitle 1", image = ""),
        FavoriteUI(id = 2, title = "Favorite Book 2", subtitle = "Subtitle 2", image = ""),
        FavoriteUI(id = 3, title = "Favorite Book 3", subtitle = "Subtitle 3", image = ""),
    )

    private val uiStateWithFavorites = FavoriteUIState(
        favoriteBooks = testFavorites,
        isLoading = false,
    )

    private val loadingState = FavoriteUIState(
        isLoading = true,
    )

    private val semantics: SemanticsStrings = SemanticsStrings()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun shouldDisplayFavoriteBooks() {
        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemantics provides semantics) {
                FavoriteScreen(
                    uiState = uiStateWithFavorites,
                    onNavigateToBookDetails = {},
                )
            }
        }
        testFavorites.forEach { book ->
            composeRule.onNodeWithText(checkNotNull(book.title)).assertIsDisplayed()
            composeRule.onNodeWithText(checkNotNull(book.subtitle)).assertIsDisplayed()
        }
    }

    @Test
    fun shouldUpdateBookIdOnNavigateToBookDetails() {
        var navigatedBookId: Long? = null

        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemantics provides semantics) {
                FavoriteScreen(
                    uiState = uiStateWithFavorites,
                    onNavigateToBookDetails = { bookId -> navigatedBookId = bookId },
                )
            }
        }
        composeRule.onNodeWithText(checkNotNull(testFavorites.first().title)).performClick()
        assert(navigatedBookId == testFavorites.first().id.toLong())
    }

    @Test
    fun shouldShowLoadingIndicator() {
        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemantics provides semantics) {
                FavoriteScreen(
                    uiState = loadingState,
                    onNavigateToBookDetails = {},
                )
            }
        }
        composeRule.onNodeWithTag(semantics.favoriteBooksLoadingIndicator).assertIsDisplayed()
    }
}
