package com.joohnq.moodapp.ui.welcome

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.swipeLeft
import com.joohnq.moodapp.di.platformModule
import com.joohnq.moodapp.di.sharedModule
import com.joohnq.moodapp.ui.presentation.welcome.WelcomeScreen
import com.joohnq.moodapp.util.constants.TestConstants
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class WelcomeScreenTest : KoinTest {
    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                sharedModule, platformModule
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testHorizontalPagerWithSwipe() = runComposeUiTest {
        setContent {
            WelcomeScreen(
                userPreferenceViewModel = get<UserPreferenceViewModel>()
            )
        }
        val horizontalPager = onNodeWithTag(TestConstants.WELCOME_SCREEN_HORIZONTAL_PAGER)
        val firstScreen = onNodeWithText("Get Started")

        horizontalPager.assertExists()
        firstScreen.assertExists()

        onRoot().performTouchInput { swipeLeft() }
        onNodeWithTag(TestConstants.WELCOME_SCREEN_HEALTH_STATE).assertExists()
        onRoot().performTouchInput { swipeLeft() }
        onNodeWithTag(TestConstants.WELCOME_SCREEN_INTELLIGENT).assertExists()
        onRoot().performTouchInput { swipeLeft() }
        onNodeWithTag(TestConstants.WELCOME_SCREEN_RESOURCES).assertExists()
        onRoot().performTouchInput { swipeLeft() }
        onNodeWithTag(TestConstants.WELCOME_SCREEN_COMMUNITY).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testHorizontalPagerWithButton() = runComposeUiTest {
        setContent {
            WelcomeScreen(
                userPreferenceViewModel = get<UserPreferenceViewModel>()
            )
        }
        val horizontalPager = onNodeWithTag(TestConstants.WELCOME_SCREEN_HORIZONTAL_PAGER)
        val firstScreenButton = onNodeWithText("Get Started")

        horizontalPager.assertExists()
        firstScreenButton.assertExists()

        firstScreenButton.performClick()
        onNodeWithText("Step One").assertExists()
        onNodeWithTag(TestConstants.NEXT_BUTTON + "One").performClick()
        onNodeWithText("Step Two").assertExists()
        onNodeWithTag(TestConstants.NEXT_BUTTON + "Two").performClick()
        onNodeWithText("Step Three").assertExists()
        onNodeWithTag(TestConstants.NEXT_BUTTON + "Three").performClick()
        onNodeWithText("Step Four").assertExists()
    }
}