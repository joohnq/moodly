//package com.joohnq.moodapp.viewmodel
//
//import com.joohnq.moodapp.model.dao.StatsRecordDAO
//import com.joohnq.moodapp.entities.Mood
//import com.joohnq.moodapp.entities.StatsRecord
//import com.joohnq.moodapp.entities.SleepQuality
//import com.joohnq.moodapp.entities.StressLevel
//import com.joohnq.moodapp.view.screens.onboarding.OnboardingViewModel
//import com.varabyte.truthish.assertThat
//import dev.mokkery.answering.returns
//import dev.mokkery.everySuspend
//import dev.mokkery.matcher.any
//import dev.mokkery.mock
//import dev.mokkery.verifySuspend
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.cancel
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import kotlin.test.AfterTest
//import kotlin.test.BeforeTest
//import kotlin.test.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class OnboardingViewModelTest {
//    private lateinit var onboardingViewModel: OnboardingViewModel
//    private lateinit var statsRecordDAO: StatsRecordDAO
//    private val testDispatcher: CoroutineDispatcher = StandardTestDispatcher()
//
//    @BeforeTest
//    fun setUp() {
//        Dispatchers.setMain(testDispatcher)
//
//        statsRecordDAO = mock()
//        onboardingViewModel = OnboardingViewModel(statsRecordDAO)
//    }
//
//
//    @AfterTest
//    fun tearDown() {
//        Dispatchers.resetMain()
//        testDispatcher.cancel()
//    }
//
//    /**
//     * Test SetCurrentMood
//     * test if the parameter value mood has been added to _currentMood successfully
//     */
//    @Test
//    fun testSetCurrentMood() {
//        // Given
//        val mood: Mood = Mood.Sad
//
//        // When
//        onboardingViewModel.setStatsRecordMood(mood)
//
//        val currentMood = onboardingViewModel.statsRecord.value
//
//        // Then
//        assertThat(currentMood).isEqualTo(StatsRecord(mood = mood))
//        assertThat(currentMood.mood).isEqualTo(mood)
//    }
//
//    /**
//     * Test SetCurrentMoodSleepQuality
//     * test if the parameter value sleep quality has been added to _currentMood successfully
//     */
//    @Test
//    fun setCurrentMoodSleepQuality() {
//        // Given
//        val sleepQuality: SleepQuality = SleepQuality.Fair
//
//        // When
//        onboardingViewModel.setStatsRecordSleepQuality(sleepQuality)
//
//        val currentMood = onboardingViewModel.statsRecord.value
//
//        // Then
//        assertThat(currentMood).isEqualTo(StatsRecord(sleepQuality = sleepQuality))
//        assertThat(currentMood.sleepQuality).isEqualTo(sleepQuality)
//    }
//
//    /**
//     * Test SetCurrentMoodDescription
//     * test if the parameter value description has been added to _currentMood successfully
//     */
//    @Test
//    fun setCurrentMoodDescription() {
//        // Given
//        val description = "Lorem Description"
//
//        // When
//        onboardingViewModel.setStatsRecordDescription(description)
//
//        val currentMood = onboardingViewModel.statsRecord.value
//
//        // Then
//        assertThat(currentMood).isEqualTo(StatsRecord(description = description))
//        assertThat(currentMood.description).isEqualTo(description)
//    }
//
//    /**
//     * Test SetCurrentMoodStressLevel
//     * test if the parameter value stress level has been added to _currentMood successfully
//     */
//    @Test
//    fun setCurrentMoodStressLevel() {
//        // Given
//        val stressLevel: StressLevel = StressLevel.Three
//
//        // When
//        onboardingViewModel.setStatsRecordStressLevel(stressLevel)
//
//        val currentMood = onboardingViewModel.statsRecord.value
//
//        // Then
//        assertThat(currentMood).isEqualTo(StatsRecord(stressLevel = stressLevel))
//        assertThat(currentMood.stressLevel).isEqualTo(stressLevel)
//    }
//
//    /**
//     * Test InsertCurrentMood
//     * test insertCurrentMood with a successful execution, should return true
//     */
//    @Test
//    fun `test insertCurrentMood with a successful execution should return true`() = runTest {
//        // Given
//        val statsRecord = StatsRecord(mood = Mood.Happy)
//        statsRecordDAO = mock<StatsRecordDAO> {
//            everySuspend { insertMood(any()) } returns Unit
//        }
//        onboardingViewModel = OnboardingViewModel(statsRecordDAO)
//        onboardingViewModel.setCurrentStatsRecord(statsRecord)
//
//        // When
//        val res = onboardingViewModel.insertOnboardingStatsRecord()
//
//        // Then
//        assertThat(res).isEqualTo(true)
//
//        verifySuspend { statsRecordDAO.insertMood(statsRecord) }
//    }
//
//    /**
//     * Test InsertCurrentMood
//     * test insertCurrentMood with a successful execution, should return true
//     */
//    @Test
//    fun `test insertCurrentMood with a successful execution should return false`() = runTest {
//        // Given
//        statsRecordDAO = mock<StatsRecordDAO> {
//            everySuspend { insertMood(any()) } returns Unit
//        }
//        onboardingViewModel = OnboardingViewModel(statsRecordDAO)
//
//        // When
//        val res = onboardingViewModel.insertOnboardingStatsRecord()
//
//        // Then
//        assertThat(res).isFalse()
//    }
//
//    /**
//     * Test ResetCurrentMood
//     * test if the currentMood value has been reset to null
//     */
//    @Test
//    fun resetCurrentMood() {
//        // Given
//        onboardingViewModel.resetStatsRecord()
//
//        // Then
//        assertThat(onboardingViewModel.statsRecord.value).isEqualTo(StatsRecord)
//    }
//}
