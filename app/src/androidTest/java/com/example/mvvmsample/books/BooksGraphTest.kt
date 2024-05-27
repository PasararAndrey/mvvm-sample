@file:OptIn(ExperimentalTestApi::class)

package com.example.mvvmsample.books

import androidx.activity.compose.setContent
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.testing.TestNavHostController
import com.example.mvvmsample.MainActivity
import com.example.mvvmsample.R
import com.example.mvvmsample.navigation.destinations.MainBottomNavDestinations
import com.example.mvvmsample.navigation.graph.booksGraph
import com.example.mvvmsample.ui.theme.ComposeEducationTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BooksGraphTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun init() {
        hiltRule.inject()
        composeRule.activity.setContent {
            navController = TestNavHostController(composeRule.activity.applicationContext)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            ComposeEducationTheme {
                NavHost(navController = navController, startDestination = MainBottomNavDestinations.BooksGraph.route) {
                    booksGraph(navController)
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
            composeRule.waitUntilAtLeastOneExists(
                hasContentDescription(
                    composeRule.activity.applicationContext.getString(R.string.semantics_book_details_favorite_icon),
                ),
            )
            composeRule.onNodeWithTag(
                composeRule.activity.applicationContext.getString(R.string.semantics_book_details_title),
            ).assertTextEquals("Book title $testId")

            val favoriteNode = composeRule.onNodeWithTag(
                composeRule.activity.applicationContext.getString(
                    R.string.semantics_is_favorite,
                    false.toString(),
                ),
            )
            favoriteNode.assertExists()
            favoriteNode.performClick()
            composeRule.onNodeWithTag(
                composeRule.activity.applicationContext.getString(
                    R.string.semantics_is_favorite,
                    true.toString(),
                ),
            )
        }
}
