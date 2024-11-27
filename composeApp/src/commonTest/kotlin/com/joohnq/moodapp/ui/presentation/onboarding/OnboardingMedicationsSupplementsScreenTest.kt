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
import com.joohnq.moodapp.domain.MedicationsSupplements
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements.OnboardingMedicationsSupplementsScreen
import com.varabyte.truthish.assertThat
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class OnboardingMedicationsSupplementsScreenTest : KoinTest {
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
    private fun ComposeUiTest.perform(value: MedicationsSupplements) {
        onNodeWithTag(value.id).performClick()
        assertThat(onboardingViewModel.onboardingState.value.medicationsSupplements)
            .isEqualTo(value)
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if the continue button is not displayed initially`() = runComposeUiTest {
        setContent {
            Navigator(
                screen = OnboardingMedicationsSupplementsScreen()
            )
        }

        val continueButton = onNodeWithText("Continue")

        continueButton.assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click in option change the value in viewmodel and the continue button is displayed`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingMedicationsSupplementsScreen()
                )
            }

            val continueButton = onNodeWithText("Continue")
            continueButton.assertDoesNotExist()

            perform(MedicationsSupplements.PrescribedMedications)
            continueButton.assertExists()
            perform(MedicationsSupplements.OverTheCounterSupplements)
            perform(MedicationsSupplements.ImNotTakingAny)
            perform(MedicationsSupplements.PreferNotToSay)
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if when click in continue button go to OnboardingStressLevelScreen`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingMedicationsSupplementsScreen()
                )
            }

            val continueButton = onNodeWithText("Continue")
            continueButton.assertDoesNotExist()

            perform(MedicationsSupplements.PrescribedMedications)
            continueButton.assertExists()

            continueButton.performClick()

            onNodeWithText("5 OF 7").assertExists()
        }
}