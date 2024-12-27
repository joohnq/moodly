package com.joohnq.mood.ui.presentation.onboarding

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.mood.di.platformModule
import com.joohnq.mood.di.sharedModule
import com.joohnq.mood.di.viewModelModule
import com.joohnq.mood.domain.StressLevel
import com.joohnq.mood.ui.presentation.onboarding.onboarding_stress_level.OnboardingStressLevelScreen
import com.varabyte.truthish.assertThat
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class OnboardingStressLevelScreenTest : KoinTest {
    private lateinit var onboardingViewModel: OnboardingViewModel

    @BeforeTest
    fun setUp() {

        startKoin {
            modules(
                sharedModule, platformModule, viewModelModule
            )
        }
        onboardingViewModel = get()
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalTestApi::class)
    private fun ComposeUiTest.perform(value: StressLevel) {
        onNodeWithText(value.id.toString()).performClick()

        assertThat(onboardingViewModel.onboardingState.value.stressLevel)
            .isEqualTo(value)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on item change the value in viewmodel`() = runComposeUiTest {
        setContent {
            Navigator(
                screen = OnboardingStressLevelScreen()
            )
        }

        perform(StressLevel.One)
        perform(StressLevel.Two)
        perform(StressLevel.Three)
        perform(StressLevel.Four)
        perform(StressLevel.Five)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when continue go to OnboardingExpressionAnalysisScreen`() = runComposeUiTest {
        setContent {
            Navigator(
                screen = OnboardingStressLevelScreen()
            )
        }

        onNodeWithText("Continue").performClick()

        onNodeWithText("7 OF 7").assertExists()
    }
}