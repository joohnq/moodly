package com.joohnq.moodapp.view.onboarding

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.di.platformModule
import com.joohnq.moodapp.di.sharedModule
import com.joohnq.moodapp.view.screens.onboarding.MoodRateScreen
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class MoodRateScreenTest : KoinTest {
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

    private fun SemanticsNodeInteraction.swipeOneElementToLeft() {
        performTouchInput { swipeLeft(startX = 0f, endX = -125f) }
    }

    private fun String.moodString(): String = "I Feel $this"

    private fun SemanticsNodeInteraction.swipeOneElementToRight() {
        performTouchInput { swipeRight(startX = 0f, endX = 125f) }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testRouletteToLeft() = runComposeUiTest {
        setContent {
            MoodRateScreen(
                moodsViewModel = get<MoodsViewModel>()
            )
        }

        val roulette = onNodeWithTag(TestConstants.ONBOARDING_ROULETTE)
        onNodeWithText("Neutral".moodString()).assertExists()
        roulette.swipeOneElementToLeft()
        onNodeWithText("Happy".moodString()).assertExists()
        roulette.swipeOneElementToLeft()
        onNodeWithText("Overjoyed".moodString()).assertExists()
        roulette.swipeOneElementToLeft()
        onNodeWithText("Depressed".moodString()).assertExists()
        roulette.swipeOneElementToLeft()
        onNodeWithText("Sad".moodString()).assertExists()
        roulette.swipeOneElementToLeft()
        onNodeWithText("Neutral".moodString()).assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testRouletteToRight() = runComposeUiTest {
        setContent {
            MoodRateScreen(moodsViewModel = get())
        }

        val roulette = onNodeWithTag(TestConstants.ONBOARDING_ROULETTE)
        onNodeWithText("Neutral".moodString()).assertExists()
        roulette.swipeOneElementToRight()
        onNodeWithText("Sad".moodString()).assertExists()
        roulette.swipeOneElementToRight()
        onNodeWithText("Depressed".moodString()).assertExists()
        roulette.swipeOneElementToRight()
        onNodeWithText("Overjoyed".moodString()).assertExists()
        roulette.swipeOneElementToRight()
        onNodeWithText("Happy".moodString()).assertExists()
        roulette.swipeOneElementToRight()
        onNodeWithText("Neutral".moodString()).assertExists()
    }
}