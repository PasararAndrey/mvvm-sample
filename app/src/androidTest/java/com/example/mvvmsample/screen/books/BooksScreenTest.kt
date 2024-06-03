package com.example.mvvmsample.screen.books

import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import com.example.mvvmsample.MainActivity
import com.example.mvvmsample.ui.screen.books.BooksScreen
import com.example.mvvmsample.ui.screen.books.BooksUIState
import com.example.mvvmsample.ui.screen.books.model.BooksUI
import com.example.mvvmsample.utils.LocalSemanticsStrings
import com.example.mvvmsample.utils.SemanticsStrings
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

    private val testBooks = listOf(
        BooksUI(id = 1, title = "Book 1", subtitle = "Subtitle 1", image = ""),
        BooksUI(id = 2, title = "Book 2", subtitle = "Subtitle 2", image = ""),
        BooksUI(id = 3, title = "Book 3", subtitle = "Subtitle 3", image = ""),
    )

    private val uiState = BooksUIState(
        books = flowOf(PagingData.from(testBooks)),
    )

    private val semantics: SemanticsStrings = SemanticsStrings()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun shouldDisplayBooks() {
        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemanticsStrings provides semantics) {
                BooksScreen(
                    uiState = uiState,
                    onNavigateToBookDetails = {},
                )
            }
        }
        testBooks.forEach { book ->
            composeRule.onNodeWithText(book.title)
                .assertIsDisplayed()
            composeRule.onNodeWithText(checkNotNull(book.subtitle))
                .assertIsDisplayed()
        }
    }

    @Test
    fun shouldUpdateBookIdOnNavigateToBookDetails() {
        var navigatedBookId: Long? = null
        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemanticsStrings provides semantics) {
                BooksScreen(
                    uiState = uiState,
                    onNavigateToBookDetails = { bookId -> navigatedBookId = bookId },
                )
            }
        }
        composeRule.onNodeWithText(testBooks[0].title)
            .performClick()
        assert(navigatedBookId == testBooks[0].id)
    }

    @Test
    fun shouldShowLoadingIndicator() {
        val loadingState = BooksUIState(
            books = flowOf(PagingData.empty()),
        )

        composeRule.activity.setContent {
            CompositionLocalProvider(LocalSemanticsStrings provides semantics) {
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
