//package com.joohnq.moodapp.view.onboarding
//
//import androidx.compose.ui.test.ExperimentalTestApi
//import androidx.compose.ui.test.assertTextEquals
//import androidx.compose.ui.test.onNodeWithTag
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.performTextInput
//import androidx.compose.ui.test.runComposeUiTest
//import com.joohnq.moodapp.constants.TestConstants
//import com.joohnq.moodapp.di.platformModule
//import com.joohnq.moodapp.di.sharedModule
//import com.joohnq.moodapp.view.screens.getusername.GetUserNameScreen
//import org.koin.core.context.startKoin
//import org.koin.core.context.stopKoin
//import org.koin.test.KoinTest
//import org.koin.test.get
//import kotlin.test.AfterTest
//import kotlin.test.BeforeTest
//import kotlin.test.Test
//
//class GetUserNameScreenTest : KoinTest {
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
//    fun `test text field`() = runComposeUiTest {
//        setContent {
//            GetUserNameScreen(
//                userViewModel = get(),
//                userPreferencesViewModel = get(),
//            )
//        }
//
//        val textField = onNodeWithTag(TestConstants.TEXT_INPUT)
//
//        textField.performTextInput("Hello, World!")
//
//        textField.assertTextEquals("Hello, World!")
//    }
//
//    @OptIn(ExperimentalTestApi::class)
//    @Test
//    fun `test text field with error`() = runComposeUiTest {
//        setContent {
//            GetUserNameScreen(
//                userViewModel = get(),
//                userPreferencesViewModel = get(),
//            )
//        }
//
//        val textField = onNodeWithTag(TestConstants.TEXT_INPUT)
//        val continueButton = onNodeWithText("Continue")
//
//        continueButton.performClick()
//
//        textField.performTextInput("")
//        textField.assertTextEquals("")
//        onNodeWithText("Name is required").assertExists()
//    }
//}