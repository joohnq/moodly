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
import com.joohnq.moodapp.view.screens.onboarding.PhysicalSymptomsScreen
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class PhysicalSymptomsScreenTest : KoinTest {
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
            PhysicalSymptomsScreen(
                userViewModel = get(),
            )
        }

        val continueButton = onNodeWithText("Continue")

        continueButton.assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on 'Yes Very Painful' the continue button is displayed`() = runComposeUiTest {
        setContent {
            PhysicalSymptomsScreen(
                userViewModel = get(),
            )
        }

        val yesVeryPainfulButton = onNodeWithText("Yes, Very Painful.")
        val continueButton = onNodeWithText("Continue")

        yesVeryPainfulButton.assertExists()
        continueButton.assertDoesNotExist()

        yesVeryPainfulButton.performClick()
        continueButton.assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on 'No Physical Pain At All' the continue button is displayed`() = runComposeUiTest {
        setContent {
            PhysicalSymptomsScreen(
                userViewModel = get(),
            )
        }

        val noPhysicalPainAtAllButton = onNodeWithText("No Physical Pain At All.")
        val continueButton = onNodeWithText("Continue")

        noPhysicalPainAtAllButton.assertExists()
        continueButton.assertDoesNotExist()

        noPhysicalPainAtAllButton.performClick()
        continueButton.assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on 'Yes But just a bit' the continue button is displayed`() = runComposeUiTest {
        setContent {
            PhysicalSymptomsScreen(
                userViewModel = get(),
            )
        }

        val yesButJustABitButton = onNodeWithText("Yes, But just a bit")
        val continueButton = onNodeWithText("Continue")

        yesButJustABitButton.assertExists()
        continueButton.assertDoesNotExist()

        yesButJustABitButton.performClick()
        continueButton.assertExists()
    }
}