@file:OptIn(ExperimentalTestApi::class)

package com.example.mvvmsample.integration.books

import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.testing.TestNavHostController
import com.example.mvvmsample.MainActivity
import com.example.mvvmsample.hasImageVector
import com.example.mvvmsample.navigation.destinations.MainBottomNavDestinations
import com.example.mvvmsample.navigation.graph.booksGraph
import com.example.mvvmsample.ui.theme.ComposeEducationTheme
import com.example.mvvmsample.utils.LocalSemanticsStrings
import com.example.mvvmsample.utils.SemanticsStrings
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

    @Before
    fun init() {
        hiltRule.inject()
        composeRule.activity.setContent {
            navController = TestNavHostController(composeRule.activity.applicationContext)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            ComposeEducationTheme {
                CompositionLocalProvider(LocalSemanticsStrings provides semantics) {
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
            val testId = "1"
            composeRule.waitUntilAtLeastOneExists(hasTestTag(testId))
            composeRule.onNodeWithTag(testId).assert(hasText("Book title $testId"))
        }

    @Test
    fun shouldNavigateToBookDetailsAndUpdateFavorite() =
        runTest {
            val testId = "1"
            composeRule.waitUntilAtLeastOneExists(hasTestTag(testId))
            composeRule.onNodeWithTag(testId).performClick()
            composeRule.waitUntilAtLeastOneExists(hasTestTag(semantics.bookDetailsFavoriteIcon))
            composeRule.onNodeWithTag(semantics.bookDetailsTitle).assertTextEquals("Book title $testId")
            composeRule
                .onNode(hasTestTag(semantics.bookDetailsFavoriteIcon).and(hasImageVector(Icons.Default.FavoriteBorder)))
                .assertExists()
                .performClick()
            composeRule.waitUntilAtLeastOneExists(hasImageVector(Icons.Default.Favorite))
            composeRule.onNode(hasImageVector(Icons.Default.Favorite)).assertExists()
        }
}
