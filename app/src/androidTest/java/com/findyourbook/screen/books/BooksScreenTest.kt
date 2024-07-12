package com.findyourbook.screen.books

import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.PagingData
import com.findyourbook.MainActivity
import com.findyourbook.ui.screen.books.BooksScreen
import com.findyourbook.ui.screen.books.BooksUIState
import com.findyourbook.utils.LocalSemantics
import com.findyourbook.utils.SemanticsStrings
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BooksScreenTest {
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
    fun shouldShowLoadingIndicator() {
        val loadingState = BooksUIState(
            books = flowOf(PagingData.empty()),
        )

        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemantics provides semantics) {
                BooksScreen(
                    uiState = loadingState,
                    onNavigateToBookDetails = {},
                )
            }
            composeRule.onNodeWithTag(semantics.booksListLoadingIndicator)
                .assertIsDisplayed()
        }
    }
}
