package com.joohnq.moodapp.viewmodel

import com.joohnq.moodapp.model.dao.StatsRecordDAO
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.view.state.UiState
import com.varabyte.truthish.assertThat
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoodsViewModelTest {
    private lateinit var moodsViewModel: MoodsViewModel
    private lateinit var statsRecordDAO: StatsRecordDAO
    private val testDispatcher: CoroutineDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        statsRecordDAO = mock()
        moodsViewModel = MoodsViewModel(statsRecordDAO, testDispatcher)
    }


    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    /**
     * Test GetMoods
     * test GetMoods with a valid return value, so the mood value should be updated in each stage, Idle, Loading, Success
     */
    @Test
    fun `test getMoods with a success list response the mood value should be Idle - Loading so Success`() =
        runTest {
            // Given
            val mockMoods = listOf(
                StatsRecord(mood = Mood.Sad),
                StatsRecord(mood = Mood.Happy)
            )
            statsRecordDAO = mock<StatsRecordDAO> {
                everySuspend { getMoods() } returns flowOf(mockMoods)
            }

            moodsViewModel = MoodsViewModel(
                statsRecordDAO,
                testDispatcher
            )

            //When
            val states = mutableListOf<UiState<List<StatsRecord>>>()
            val job = launch(UnconfinedTestDispatcher(testScheduler)) {
                moodsViewModel.statsRecords.toList(states)
            }

            moodsViewModel.getMoods()

            testScheduler.advanceUntilIdle()

            // Then
            assertThat(states.size).isEqualTo(3)
            assertThat(states[0]).isEqualTo(UiState.Idle)
            assertThat(states[1]).isEqualTo(UiState.Loading)
            assertThat(states[2]).isEqualTo(UiState.Success(mockMoods))

            verifySuspend { statsRecordDAO.getMoods() }

            job.cancel()
        }

    /**
     * Test GetMoods
     * test GetMoods with a invalid return value, so the mood value should be updated in each stage, Idle, Loading, Failure
     */
    @Test
    fun `test getMoods with a failure response the mood value should be Idle - Loading so Failure`() =
        runTest {
            // Given
            val exception = "Something went wrong"
            statsRecordDAO = mock<StatsRecordDAO> {
                everySuspend { getMoods() } returns flow { throw Exception(exception) }
            }

            moodsViewModel = MoodsViewModel(
                statsRecordDAO,
                testDispatcher
            )

            // When
            val states = mutableListOf<UiState<List<StatsRecord>>>()
            val job = launch(UnconfinedTestDispatcher(testScheduler)) {
                moodsViewModel.statsRecords.toList(states)
            }

            moodsViewModel.getMoods()

            testScheduler.advanceUntilIdle()

            // Then
            assertThat(states.size).isEqualTo(3)
            assertThat(states[0]).isEqualTo(UiState.Idle)
            assertThat(states[1]).isEqualTo(UiState.Loading)
            assertThat(states[2]).isEqualTo(UiState.Error(exception))
            assertThat((states[2] as UiState.Error).message).isEqualTo(exception)

            verifySuspend { statsRecordDAO.getMoods() }

            job.cancel()
        }
}
