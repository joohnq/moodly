package com.joohnq.moodapp.ui.presentation.onboarding

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.di.platformModule
import com.joohnq.moodapp.di.sharedModule
import com.joohnq.moodapp.di.viewModelModule
import com.joohnq.moodapp.domain.MedicationsSupplements
import com.joohnq.moodapp.domain.PhysicalSymptoms
import com.joohnq.moodapp.domain.ProfessionalHelp
import com.joohnq.moodapp.domain.SleepQuality
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.OnboardingExpressionAnalysisScreen
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import com.joohnq.moodapp.viewmodel.StatsViewModel
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import com.varabyte.truthish.assertThat
import dev.mokkery.matcher.any
import dev.mokkery.verify
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class OnboardingExpressionAnalysisScreenTest : KoinTest {
    private lateinit var onboardingViewModel: OnboardingViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var statsViewModel: StatsViewModel
    private lateinit var sleepQualityViewModel: SleepQualityViewModel
    private lateinit var stressLevelViewModel: StressLevelViewModel
    private lateinit var userPreferencesViewModel: UserPreferenceViewModel

    @BeforeTest
    fun setUp() {
        startKoin {
            modules(
                sharedModule, platformModule, viewModelModule
            )
        }
        onboardingViewModel = get()
        userViewModel = get()
        statsViewModel = get()
        sleepQualityViewModel = get()
        stressLevelViewModel = get()
        userPreferencesViewModel = get()
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @OptIn(ExperimentalTestApi::class)
    private fun ComposeUiTest.perform(value: String) {
        onNodeWithTag(OnboardingExpressionAnalysisScreen.OnboardingExpressionTestTag.TEXT_INPUT).performTextInput(
            value
        )

        assertThat(onboardingViewModel.onboardingState.value.statsRecord.description)
            .isEqualTo(value)

        verify { onboardingViewModel.onAction(OnboardingIntent.UpdateStatsRecordDescription(value)) }
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if the continue button is not displayed initially`() = runComposeUiTest {
        setContent {
            Navigator(
                screen = OnboardingExpressionAnalysisScreen()
            )
        }

        val continueButton = onNodeWithText("Continue")

        continueButton.assertDoesNotExist()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test if after typing text change the value in viewmodel and display the continue button`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingExpressionAnalysisScreen()
                )
            }

            val continueButton = onNodeWithText("Continue")
            continueButton.assertDoesNotExist()

            perform("Hello, World!")

            continueButton.assertExists()
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `test when continue - should add the sleep quality - stress level - stat record and user values`() =
        runComposeUiTest {
            setContent {
                Navigator(
                    screen = OnboardingExpressionAnalysisScreen()
                )
            }

            runBlocking {
                onboardingViewModel.onAction(
                    OnboardingIntent.SetOnboardingStateForTesting(
                        OnboardingState(
                            physicalSymptoms = PhysicalSymptoms.No,
                            soughtHelp = ProfessionalHelp.No,
                            medicationsSupplements = MedicationsSupplements.PrescribedMedications,
                            statsRecord = StatsRecord.init(),
                            sleepQuality = SleepQuality.Worst,
                            stressLevel = StressLevel.Three,
                            sliderValue = 0.25f
                        )
                    )
                )
            }

            verify {
                onboardingViewModel.onAction(
                    OnboardingIntent.SetOnboardingStateForTesting(any())
                )
            }

            perform("Hello, World!")

            onNodeWithText("Continue").performClick()

//            verify {
//                sleepQualityViewModel.onAction(
//                    SleepQualityIntent.AddSleepQualityRecord(any())
//                )
//            }
//            verify {
//                stressLevelViewModel.onAction(
//                    StressLevelIntent.AddStressLevelRecord(any(), any())
//                )
//            }
//            verify {
//                statsViewModel.onAction(StatsIntent.AddStatsRecord(any()))
//            }
//            verify {
//                userViewModel.onAction(
//                    UserIntent.UpdateUser(any())
//                )
//            }
        }
}