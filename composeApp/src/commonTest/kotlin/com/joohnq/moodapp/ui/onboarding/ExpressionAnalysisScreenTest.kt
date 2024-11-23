//package com.joohnq.moodapp.view.onboarding
//
//import androidx.compose.ui.test.ExperimentalTestApi
//import androidx.compose.ui.test.assertIsNotDisplayed
//import androidx.compose.ui.test.assertTextEquals
//import androidx.compose.ui.test.hasContentDescription
//import androidx.compose.ui.test.hasText
//import androidx.compose.ui.test.onNodeWithTag
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.performTextInput
//import androidx.compose.ui.test.runComposeUiTest
//import com.joohnq.moodapp.constants.TestConstants
//import com.joohnq.moodapp.di.platformModule
//import com.joohnq.moodapp.di.sharedModule
//import com.joohnq.moodapp.view.screens.onboarding.ExpressionAnalysisScreen
//import org.koin.core.context.startKoin
//import org.koin.core.context.stopKoin
//import org.koin.test.KoinTest
//import org.koin.test.get
//import kotlin.test.AfterTest
//import kotlin.test.BeforeTest
//import kotlin.test.Test
//
//class ExpressionAnalysisScreenTest : KoinTest {
//    @BeforeTest
//    fun setUp() {
//        startKoin {
//            modules(
//                sharedModule, platformModule
//            )
//        }
//    }
//
//    @AfterTest
//    fun tearDown() {
//        stopKoin()
//    }
//
//    @OptIn(ExperimentalTestApi::class)
//    @Test
//    fun `test if the continue button is not displayed initially`() = runComposeUiTest {
//        setContent {
//            ExpressionAnalysisScreen(
//                moodsViewModel = get(),
//            )
//        }
//
//        val continueButton = onNodeWithText("Continue")
//
//        continueButton.assertDoesNotExist()
//    }
//
//    @OptIn(ExperimentalTestApi::class)
//    @Test
//    fun `test if after digit text show continue button`() = runComposeUiTest {
//        setContent {
//            ExpressionAnalysisScreen(
//                moodsViewModel = get(),
//                userPreferencesViewModel = get(),
//            )
//        }
//
//        val continueButton = onNodeWithText("Continue")
//        val textField = onNodeWithTag(TestConstants.TEXT_INPUT)
//        continueButton.assertDoesNotExist()
//
//        textField.performTextInput("Hello, World!")
//
//        textField.assertTextEquals("Hello, World!")
//        continueButton.assertExists()
//    }
//
//}