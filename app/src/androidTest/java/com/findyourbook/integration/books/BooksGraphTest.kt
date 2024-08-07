@file:OptIn(ExperimentalTestApi::class)

package com.findyourbook.integration.books

import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.testing.TestNavHostController
import com.findyourbook.MainActivity
import com.findyourbook.fake.FakeBooksService
import com.findyourbook.hasImageVector
import com.findyourbook.navigation.destinations.MainBottomNavDestinations
import com.findyourbook.navigation.graph.booksGraph
import com.findyourbook.ui.theme.ComposeEducationTheme
import com.findyourbook.utils.LocalSemantics
import com.findyourbook.utils.SemanticsStrings
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalTestApi
@HiltAndroidTest
class BooksGraphTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController
    private val semantics: SemanticsStrings = SemanticsStrings()
    private val fakeBook = FakeBooksService.books.first().first()

    @Before
    fun init() {
        hiltRule.inject()
        composeRule.activity.setContent {
            navController = TestNavHostController(composeRule.activity.applicationContext)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            ComposeEducationTheme {
                CompositionLocalProvider(LocalSemantics provides semantics) {
                    NavHost(
                        navController = navController,
                        startDestination = MainBottomNavDestinations.BooksGraph.route,
                    ) {
                        booksGraph(navController)
                    }
                }
            }
        }
    }

    @Test
    fun shouldLoadFirstPageCorrectly() =
        runTest {
            composeRule.waitUntilAtLeastOneExists(hasTestTag(fakeBook.id.toString()))
            composeRule.onNodeWithText(fakeBook.title, substring = true).assertExists()
        }

    @Test
    fun shouldNavigateToBookDetails() =
        runTest {
            composeRule.waitUntilAtLeastOneExists(hasTestTag(fakeBook.id.toString()))
            composeRule.onNodeWithTag(fakeBook.id.toString()).performClick()
            composeRule.waitUntilAtLeastOneExists(hasTestTag(semantics.bookDetailsFavoriteIcon))
            composeRule.onNodeWithTag(semantics.bookDetailsTitle).assertTextEquals(fakeBook.title)
            composeRule.onNode(
                hasTestTag(semantics.bookDetailsFavoriteIcon).and(hasImageVector(Icons.Default.FavoriteBorder)),
            ).assertExists()
        }

    @Test
    fun shouldNavigateToBookDetailsAndUpdateFavoriteIcon() =
        runTest {
            composeRule.waitUntilAtLeastOneExists(hasTestTag(fakeBook.id.toString()))
            composeRule.onNodeWithTag(fakeBook.id.toString()).performClick()
            composeRule.waitUntilAtLeastOneExists(hasTestTag(semantics.bookDetailsFavoriteIcon))
            composeRule.onNodeWithTag(semantics.bookDetailsTitle).assertTextEquals(fakeBook.title)
            composeRule.onNode(hasImageVector(Icons.Default.FavoriteBorder)).performClick()
            composeRule.waitUntilAtLeastOneExists(hasImageVector(Icons.Default.Favorite))
            composeRule.onNode(hasImageVector(Icons.Default.Favorite)).assertExists()
        }
}
