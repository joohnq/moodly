package com.joohnq.moodapp.ui.presentation.onboarding

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.di.platformModule
import com.joohnq.moodapp.di.sharedModule
import com.joohnq.moodapp.domain.PhysicalSymptoms
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_physical_symptoms.OnboardingPhysicalSymptomsScreen
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import com.varabyte.truthish.assertThat
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class OnboardingPhysicalSymptomsScreenTest : KoinTest {
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

    @OptIn(ExperimentalTestApi::class) fun ComposeUiTest.perform(
        value: String,
        expect: PhysicalSymptoms
    ) {
        onNodeWithTag(value).performClick()
        assertThat(onboardingViewModel.onboardingState.value.physicalSymptoms)
            .isEqualTo(expect)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if the continue button is not displayed initially`() = runComposeUiTest {
        setContent {
            Navigator(
                screen = OnboardingPhysicalSymptomsScreen()
            )
        }

        val continueButton = onNodeWithText("Continue")

        continueButton.assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click on option change the value in viewmodel and the continue button is displayed`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingPhysicalSymptomsScreen()
                )
            }

            val continueButton = onNodeWithText("Continue")

            continueButton.assertDoesNotExist()

            perform(PhysicalSymptoms.YesVeryPainful.id, PhysicalSymptoms.YesVeryPainful)
            perform(PhysicalSymptoms.YesJustABit.id, PhysicalSymptoms.YesJustABit)
            perform(PhysicalSymptoms.No.id, PhysicalSymptoms.No)

            continueButton.assertExists()
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click in continue button go to OnboardingSleepQualityScreen`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingPhysicalSymptomsScreen()
                )
            }

            val continueButton = onNodeWithText("Continue")
            continueButton.assertDoesNotExist()

            perform(PhysicalSymptoms.YesVeryPainful.id, PhysicalSymptoms.YesVeryPainful)
            continueButton.assertExists()

            continueButton.performClick()

            onNodeWithText("4 OF 7").assertExists()
        }
}