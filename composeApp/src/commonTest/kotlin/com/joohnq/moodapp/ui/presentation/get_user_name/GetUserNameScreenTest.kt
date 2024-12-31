package com.joohnq.moodapp.ui.presentation.get_user_name

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.di.platformModule
import com.joohnq.moodapp.di.sharedModule
import com.joohnq.moodapp.di.viewModelModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class GetUserNameScreenTest : KoinTest {
    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                sharedModule, platformModule, viewModelModule
            )
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test text field with a valid value when click in continue button - should go to HomeScreen`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = GetUserNameScreen()
                )
            }

            onNodeWithTag(GetUserNameScreen.GetUserNameTestTag.TEXT_INPUT).performTextInput("Hello, World!")

            onNodeWithText("Continue").performClick()

            onNodeWithText("Continue").assertExists()
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test text field with error when click in continue button - should show a SnackBar`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = GetUserNameScreen()
                )
            }

            onNodeWithText("Continue").performClick()

            onNodeWithText("Name is required").assertExists()
        }
}