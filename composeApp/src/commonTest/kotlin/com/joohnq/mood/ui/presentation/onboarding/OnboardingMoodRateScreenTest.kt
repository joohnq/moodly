package com.joohnq.mood.ui.presentation.onboarding

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.mood.di.platformModule
import com.joohnq.mood.di.sharedModule
import com.joohnq.mood.domain.Mood
import com.joohnq.mood.ui.presentation.onboarding.onboarding_mood_rate.OnboardingMoodRateScreen
import com.varabyte.truthish.assertThat
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class OnboardingMoodRateScreenTest : KoinTest {
    private lateinit var onboardingViewModel: OnboardingViewModel

    @BeforeTest
    fun setUp() {
        val testViewModelModule = module {
            single<OnboardingViewModel> {
                OnboardingViewModel()
            }
        }
        startKoin {
            modules(
                sharedModule, platformModule, testViewModelModule
            )
        }
        onboardingViewModel = get()
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
    private fun ComposeUiTest.performToLeft(
        roulette: SemanticsNodeInteraction,
        value: String,
        expect: Mood
    ) {
        roulette.swipeOneElementToLeft()
        onNodeWithText(value.moodString()).assertExists()
        assertThat(onboardingViewModel.onboardingState.value.statsRecord.mood).isEqualTo(expect)
    }

    @OptIn(ExperimentalTestApi::class)
    private fun ComposeUiTest.performToRight(
        roulette: SemanticsNodeInteraction,
        value: String,
        expect: Mood
    ) {
        roulette.swipeOneElementToRight()
        onNodeWithText(value.moodString()).assertExists()
        assertThat(onboardingViewModel.onboardingState.value.statsRecord.mood).isEqualTo(expect)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test swiping roulette to left - should change the mood`() = runComposeUiTest {
        setContent {
            Navigator(
                screen = OnboardingMoodRateScreen()
            )
        }

        val roulette = onNodeWithTag(OnboardingMoodRateScreen.OnboardingMoodRateTestTag.ROULETTE)

        onNodeWithText("Neutral".moodString()).assertExists()
        assertThat(onboardingViewModel.onboardingState.value.statsRecord.mood).isEqualTo(Mood.Neutral)

        performToLeft(roulette, "Happy", Mood.Happy)
        performToLeft(roulette, "Overjoyed", Mood.Overjoyed)
        performToLeft(roulette, "Depressed", Mood.Depressed)
        performToLeft(roulette, "Sad", Mood.Sad)
        performToLeft(roulette, "Neutral", Mood.Neutral)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test swiping roulette to right - should change the mood`() = runComposeUiTest {
        setContent {
            Navigator(
                screen = OnboardingMoodRateScreen()
            )
        }

        val roulette = onNodeWithTag(OnboardingMoodRateScreen.OnboardingMoodRateTestTag.ROULETTE)

        onNodeWithText("Neutral".moodString()).assertExists()
        assertThat(onboardingViewModel.onboardingState.value.statsRecord.mood).isEqualTo(Mood.Neutral)

        performToRight(roulette, "Sad", Mood.Sad)
        performToRight(roulette, "Depressed", Mood.Depressed)
        performToRight(roulette, "Overjoyed", Mood.Overjoyed)
        performToRight(roulette, "Happy", Mood.Happy)
        performToRight(roulette, "Neutral", Mood.Neutral)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when continue go to OnboardingProfessionalHelpScreen`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingMoodRateScreen()
                )
            }

            // Click to go to the next page
            onNodeWithContentDescription("Go Next").assertExists()
            onNodeWithContentDescription("Go Next").onParent().performMouseInput { click() }

            //Verify if went to OnboardingProfessionalHelpScreen
            onNodeWithText("2 OF 7").assertExists()
        }
}