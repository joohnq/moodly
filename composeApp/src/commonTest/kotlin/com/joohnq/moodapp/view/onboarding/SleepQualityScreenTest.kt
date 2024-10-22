package com.joohnq.moodapp.view.onboarding

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.requestFocus
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertValueEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.di.platformModule
import com.joohnq.moodapp.di.sharedModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class SleepQualityScreenTest : KoinTest {
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
    fun `test if the continue button is not displayed initially`() = runComposeUiTest {
        setContent {
            SleepQualityScreen(
                moodsViewModel = get(),
            )
        }

        val slider = onNodeWithTag(TestConstants.SLEEP_QUALITY_SLIDER)

        slider.assertIsDisplayed()
//        slider.assertValueEquals("0")
        slider.performTouchInput {
            swipeLeft()
        }
        onNodeWithTag(TestConstants.SLEEP_QUALITY_SLIDER).performTouchInput {
            swipeLeft()
        }
//        slider.performSemanticsAction(SemanticsActions.SetProgress) { it(0.25f) }
//        slider.assertValueEquals("0.25")
        println(slider)
        println(onNodeWithTag(TestConstants.SLEEP_QUALITY_SLIDER).fetchSemanticsNode().config)

    }
}