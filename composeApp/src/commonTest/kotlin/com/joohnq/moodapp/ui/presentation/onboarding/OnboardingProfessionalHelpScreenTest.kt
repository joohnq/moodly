package com.joohnq.moodapp.ui.presentation.onboarding

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.di.platformModule
import com.joohnq.moodapp.di.sharedModule
import com.joohnq.moodapp.domain.ProfessionalHelp
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.OnboardingProfessionalHelpScreen
import com.varabyte.truthish.assertThat
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class OnboardingProfessionalHelpScreenTest : KoinTest {
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
    fun `test if the continue button is not displayed initially`() = runComposeUiTest {
        setContent {
            Navigator(
                screen = OnboardingProfessionalHelpScreen()
            )
        }

        val continueButton = onNodeWithText("Continue")

        continueButton.assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on option change the value on viewmodel and the continue button is displayed`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingProfessionalHelpScreen()
                )
            }

            val continueButton = onNodeWithText("Continue")

            continueButton.assertDoesNotExist()

            onNodeWithText("Yes").performClick()
            assertThat(onboardingViewModel.onboardingState.value.soughtHelp)
                .isEqualTo(ProfessionalHelp.Yes)

            continueButton.assertExists()

            onNodeWithText("No").performClick()
            assertThat(onboardingViewModel.onboardingState.value.soughtHelp)
                .isEqualTo(ProfessionalHelp.No)
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on continue button go to OnboardingPhysicalSymptomsScreen`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingProfessionalHelpScreen()
                )
            }

            val continueButton = onNodeWithText("Continue")

            continueButton.assertDoesNotExist()
            onNodeWithText("No").performClick()
            continueButton.assertExists()

            continueButton.performClick()

            onNodeWithText("3 OF 7").assertExists()
        }
}