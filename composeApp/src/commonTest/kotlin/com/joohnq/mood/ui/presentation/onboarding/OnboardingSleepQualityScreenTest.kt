package com.joohnq.mood.ui.presentation.onboarding

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.runComposeUiTest
import androidx.compose.ui.test.swipeUp
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.mood.di.platformModule
import com.joohnq.mood.di.sharedModule
import com.joohnq.mood.domain.SleepQuality
import com.joohnq.mood.ui.presentation.onboarding.onboarding_sleep_quality.OnboardingSleepQualityScreen
import com.varabyte.truthish.assertThat
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class OnboardingSleepQualityScreenTest : KoinTest {
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

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when change the slider value change in the viewmodel`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingSleepQualityScreen()
                )
            }

            val slider =
                onNodeWithTag(OnboardingSleepQualityScreen.OnboardingSleepQualityTestTag.SLEEP_QUALITY_SLIDER)

            assertThat(onboardingViewModel.onboardingState.value.sleepQuality)
                .isEqualTo(SleepQuality.Worst)

            slider.assertIsDisplayed()
            slider.performTouchInput { swipeUp(startY = 100f, 0f) }

            assertThat(onboardingViewModel.onboardingState.value.sleepQuality)
                .isEqualTo(SleepQuality.Excellent)

        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on continue go to OnboardingMedicationsSupplementsScreen`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingSleepQualityScreen()
                )
            }

            onNodeWithText("Continue").performClick()

            onNodeWithText("5 OF 7").assertExists()
        }
}