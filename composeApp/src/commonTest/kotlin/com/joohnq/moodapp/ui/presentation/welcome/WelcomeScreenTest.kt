package com.joohnq.moodapp.ui.presentation.welcome

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.swipeLeft
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.data.repository.UserPreferencesRepository
import com.joohnq.moodapp.di.platformModule
import com.joohnq.moodapp.di.sharedModule
import com.joohnq.moodapp.di.viewModelModule
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class WelcomeScreenTest : KoinTest {
    private lateinit var userPreferenceRepository: UserPreferencesRepository
    private lateinit var userPreferenceViewModel: UserPreferenceViewModel

    @BeforeTest
    fun setUp() {
        userPreferenceRepository = mock()
        val testViewModelModule = module {
            single<UserPreferenceViewModel> {
                UserPreferenceViewModel(
                    userPreferencesRepository = userPreferenceRepository,
                )
            }
        }
        startKoin {
            modules(
                sharedModule, platformModule, viewModelModule, testViewModelModule
            )
        }
        userPreferenceViewModel = get<UserPreferenceViewModel>()
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }


    @OptIn(ExperimentalTestApi::class)
    private fun ComposeUiTest.navigateToLastPage(firstScreenButton: SemanticsNodeInteraction) {
        firstScreenButton.performClick()
        onNodeWithText("STEP ONE").assertExists()
        onNodeWithTag(WelcomeScreen.WelcomeTestTag.GO_NEXT + 1).performClick()
        onNodeWithText("STEP TWO").assertExists()
        onNodeWithTag(WelcomeScreen.WelcomeTestTag.GO_NEXT + 2).performClick()
        onNodeWithText("STEP THREE").assertExists()
        onNodeWithTag(WelcomeScreen.WelcomeTestTag.GO_NEXT + 3).performClick()
        onNodeWithText("STEP FOUR").assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testHorizontalPagerWithSwipe() = runComposeUiTest {
        setContent {
            Navigator(
                screen = WelcomeScreen()
            )
        }
        val horizontalPager =
            onNodeWithTag(WelcomeScreen.WelcomeTestTag.HORIZONTAL_PAGER)
        val firstScreen = onNodeWithText("Get Started")

        horizontalPager.assertExists()
        firstScreen.assertExists()

        onRoot().performTouchInput { swipeLeft() }
        onNodeWithText("STEP ONE").assertExists()
        onRoot().performTouchInput { swipeLeft() }
        onNodeWithText("STEP TWO").assertExists()
        onRoot().performTouchInput { swipeLeft() }
        onNodeWithText("STEP THREE").assertExists()
        onRoot().performTouchInput { swipeLeft() }
        onNodeWithText("STEP FOUR").assertExists()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testHorizontalPagerWithButton() = runComposeUiTest {
        setContent {
            Navigator(
                screen = WelcomeScreen()
            )
        }
        val horizontalPager =
            onNodeWithTag(WelcomeScreen.WelcomeTestTag.HORIZONTAL_PAGER)
        val firstScreenButton = onNodeWithText("Get Started")

        horizontalPager.assertExists()
        firstScreenButton.assertExists()

        navigateToLastPage(firstScreenButton)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `testing updating skip welcome screen with a success request - should be true and go to OnboardingMoodRateScreen `() =
        runComposeUiTest {
            everySuspend { userPreferenceRepository.updateSkipWelcomeScreen(any()) } returns true
            setContent {
                Navigator(
                    screen = WelcomeScreen()
                )
            }

            val horizontalPager =
                onNodeWithTag(WelcomeScreen.WelcomeTestTag.HORIZONTAL_PAGER)
            val firstScreenButton = onNodeWithText("Get Started")

            horizontalPager.assertExists()
            firstScreenButton.assertExists()

            navigateToLastPage(firstScreenButton)
            onNodeWithTag(WelcomeScreen.WelcomeTestTag.GO_NEXT + 4).performClick()
            verifySuspend { userPreferenceRepository.updateSkipWelcomeScreen(any()) }
            onNodeWithText("I Feel Neutral").assertExists()
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `testing updating skip welcome screen with a failed request - should be false and show a SnackBar`() =
        runComposeUiTest {
            everySuspend { userPreferenceRepository.updateSkipWelcomeScreen(any()) } returns false
            setContent {
                Navigator(
                    screen = WelcomeScreen()
                )
            }

            val horizontalPager =
                onNodeWithTag(WelcomeScreen.WelcomeTestTag.HORIZONTAL_PAGER)
            val firstScreenButton = onNodeWithText("Get Started")

            horizontalPager.assertExists()
            firstScreenButton.assertExists()

            navigateToLastPage(firstScreenButton)
            onNodeWithTag(WelcomeScreen.WelcomeTestTag.GO_NEXT + 4).performClick()
            verifySuspend { userPreferenceRepository.updateSkipWelcomeScreen(any()) }
            onNodeWithTag(WelcomeScreen.WelcomeTestTag.SNACK_BAR).assertExists()
        }
}