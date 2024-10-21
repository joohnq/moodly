package com.joohnq.moodapp.view.welcome

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import com.joohnq.moodapp.constants.TestConstants
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class WelcomeScreenTest {

    @Test
    fun testHorizontalPagerWithSwipe() = runComposeUiTest {
        setContent {
            WelcomeScreen()
        }
        val horizontalPager = onNodeWithTag(TestConstants.WELCOME_SCREEN_HORIZONTAL_PAGER)
        val firstScreen = onNodeWithText("Welcome to the ultimate")

        horizontalPager.assertExists()
        firstScreen.assertExists()

    }
}