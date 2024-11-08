//package com.joohnq.moodapp.view.onboarding
//
//import androidx.compose.ui.test.ExperimentalTestApi
//import androidx.compose.ui.test.assertIsNotDisplayed
//import androidx.compose.ui.test.hasContentDescription
//import androidx.compose.ui.test.hasText
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.runComposeUiTest
//import com.joohnq.moodapp.di.platformModule
//import com.joohnq.moodapp.di.sharedModule
//import com.joohnq.moodapp.view.screens.onboarding.MedicationsSupplementsScreen
//import org.koin.core.context.startKoin
//import org.koin.core.context.stopKoin
//import org.koin.test.KoinTest
//import org.koin.test.get
//import kotlin.test.AfterTest
//import kotlin.test.BeforeTest
//import kotlin.test.Test
//
//class MedicationsSupplementsScreenTest : KoinTest {
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
//            MedicationsSupplementsScreen(
//                userViewModel = get(),
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
//    fun `test if when click on option 1 the continue button is displayed`() = runComposeUiTest {
//        setContent {
//            MedicationsSupplementsScreen(
//                userViewModel = get(),
//            )
//        }
//
//        val option1Button = onNodeWithText("Prescribed Medications")
//        val continueButton = onNodeWithText("Continue")
//
//        option1Button.assertExists()
//        continueButton.assertDoesNotExist()
//
//        option1Button.performClick()
//        continueButton.assertExists()
//    }
//
//    @OptIn(ExperimentalTestApi::class)
//    @Test
//    fun `test if when click on option 2 the continue button is displayed`() = runComposeUiTest {
//        setContent {
//            MedicationsSupplementsScreen(
//                userViewModel = get(),
//            )
//        }
//
//        val option2 = onNodeWithText("Over the Counter Supplements")
//        val continueButton = onNodeWithText("Continue")
//
//        option2.assertExists()
//        continueButton.assertDoesNotExist()
//
//        option2.performClick()
//        continueButton.assertExists()
//    }
//
//    @OptIn(ExperimentalTestApi::class)
//    @Test
//    fun `test if when click on option 3 the continue button is displayed`() = runComposeUiTest {
//        setContent {
//            MedicationsSupplementsScreen(
//                userViewModel = get(),
//            )
//        }
//
//        val option3 = onNodeWithText("I'm Not Taking Any")
//        val continueButton = onNodeWithText("Continue")
//
//        option3.assertExists()
//        continueButton.assertDoesNotExist()
//
//        option3.performClick()
//        continueButton.assertExists()
//    }
//
//    @OptIn(ExperimentalTestApi::class)
//    @Test
//    fun `test if when click on option 4 the continue button is displayed`() = runComposeUiTest {
//        setContent {
//            MedicationsSupplementsScreen(
//                userViewModel = get(),
//            )
//        }
//
//        val option4 = onNodeWithText("Prefer Not To Say")
//        val continueButton = onNodeWithText("Continue")
//
//        option4.assertExists()
//        continueButton.assertDoesNotExist()
//
//        option4.performClick()
//        continueButton.assertExists()
//    }
//}