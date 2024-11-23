//package com.joohnq.moodapp.view.onboarding
//
//import androidx.compose.ui.test.ExperimentalTestApi
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.runComposeUiTest
//import com.joohnq.moodapp.di.platformModule
//import com.joohnq.moodapp.di.sharedModule
//import com.joohnq.moodapp.view.screens.onboarding.StressLevelScreen
//import org.koin.core.context.startKoin
//import org.koin.core.context.stopKoin
//import org.koin.test.KoinTest
//import org.koin.test.get
//import kotlin.test.AfterTest
//import kotlin.test.BeforeTest
//import kotlin.test.Test
//
//class StressRateScreenTest : KoinTest {
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
//    fun `test if start in 3`() = runComposeUiTest {
//        setContent {
//            StressLevelScreen(
//                moodsViewModel = get(),
//            )
//        }
//
//        val continueButton = onNodeWithText("Continue")
//
//        continueButton.assertDoesNotExist()
//    }
//
//}