package com.joohnq.moodapp.view.onboarding

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.joohnq.moodapp.di.platformModule
import com.joohnq.moodapp.di.sharedModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ProfessionalHelpScreenTest : KoinTest {

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
            ProfessionalHelpScreen(
                userViewModel = get(),
            )
        }

        val yesButton = onNodeWithText("Yes")
        val noButton = onNodeWithText("No")
        val continueButton = onNodeWithText("Continue")

        yesButton.assertExists()
        noButton.assertExists()
        continueButton.assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on yes the continue button is displayed`() = runComposeUiTest {
        setContent {
            ProfessionalHelpScreen(
                userViewModel = get(),
            )
        }

        val yesButton = onNodeWithText("Yes")
        val continueButton = onNodeWithText("Continue")

        yesButton.assertExists()
        continueButton.assertDoesNotExist()

        yesButton.performClick()
        continueButton.assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on no the continue button is displayed`() = runComposeUiTest {
        setContent {
            ProfessionalHelpScreen(
                userViewModel = get(),
            )
        }

        val noButton = onNodeWithText("No")
        val continueButton = onNodeWithText("Continue")

        noButton.assertExists()
        continueButton.assertDoesNotExist()

        noButton.performClick()
        continueButton.assertExists()
    }
}