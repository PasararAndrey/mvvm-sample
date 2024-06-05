package com.example.mvvmsample

import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.mvvmsample.navigation.destinations.MainBottomNavDestinations
import com.example.mvvmsample.ui.app.AppContent
import com.example.mvvmsample.utils.LocalSemantics
import com.example.mvvmsample.utils.SemanticsStrings
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BottomNavigationTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController
    private val screens = MainBottomNavDestinations.destinations()

    private val semantics: SemanticsStrings = SemanticsStrings()

    @Before
    fun init() {
        hiltRule.inject()
        composeRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current.applicationContext)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CompositionLocalProvider(LocalSemantics provides semantics) {
                AppContent(screens, navController)
            }
        }
    }

    @Test
    fun shouldSetupStartDestination() {
        composeRule.onNodeWithTag(semantics.homeScreen).assertIsDisplayed()
        composeRule.onNodeWithTag(screens.first().route).assertIsSelected()
    }

    @Test
    fun shouldChangeBottomIconSelectionOnNavigation() {
        composeRule.onNodeWithTag(screens[1].route).performClick()
        composeRule.onNodeWithTag(screens[1].route).assertIsSelected()
    }
}
