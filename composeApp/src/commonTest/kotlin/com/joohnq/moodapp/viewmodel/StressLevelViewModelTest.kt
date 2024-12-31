package com.joohnq.moodapp.viewmodel

import app.cash.turbine.test
import com.joohnq.moodapp.state.UiState
import com.joohnq.moodapp.data.repository.StressLevelRepository
import com.joohnq.moodapp.domain.StressLevelRecord
import com.varabyte.truthish.assertThat
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class StressLevelViewModelTest {
    private lateinit var stressLevelRepository: StressLevelRepository
    private lateinit var stressLevelViewModel: StressLevelViewModel

    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        stressLevelRepository = mock()
        stressLevelViewModel = StressLevelViewModel(stressLevelRepository, dispatcher)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun mockList(count: Int): List<StressLevelRecord> {
        val list = mutableListOf<StressLevelRecord>()
        for (i in 0..count) {
            list.add(StressLevelRecord.init())
        }
        return list
    }

    @Test
    fun `test getStressLevelRecords with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val items = mockList(10)
            everySuspend { stressLevelRepository.getStressLevels() } returns items

            stressLevelViewModel.stressLevelState.test {
                assertThat(awaitItem()).isEqualTo(StressLevelState())

                stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)

                assertThat(awaitItem()).isEqualTo(StressLevelState(stressLevelRecords = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    StressLevelState(
                        stressLevelRecords = UiState.Success(items)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test getStressLevelRecords with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            val error = "Something went wrong"
            everySuspend { stressLevelRepository.getStressLevels() } throws Exception(error)

            stressLevelViewModel.stressLevelState.test {
                assertThat(awaitItem()).isEqualTo(StressLevelState())

                stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)

                assertThat(awaitItem()).isEqualTo(StressLevelState(stressLevelRecords = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    StressLevelState(
                        stressLevelRecords = UiState.Error(error)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test addStressLevelRecord with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val item = StressLevelRecord.init()
            everySuspend { stressLevelRepository.addStressLevel(any<StressLevelRecord>()) } returns true

            stressLevelViewModel.stressLevelState.test {
                assertThat(awaitItem()).isEqualTo(StressLevelState())

                stressLevelViewModel.onAction(StressLevelIntent.AddStressLevelRecord(item))

                assertThat(awaitItem()).isEqualTo(StressLevelState(adding = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(StressLevelState(adding = UiState.Success(true)))

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test addStressLevelRecord with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            val item = StressLevelRecord.init()
            everySuspend { stressLevelRepository.addStressLevel(any<StressLevelRecord>()) } returns false

            stressLevelViewModel.stressLevelState.test {
                assertThat(awaitItem()).isEqualTo(StressLevelState())

                stressLevelViewModel.onAction(StressLevelIntent.AddStressLevelRecord(item))

                assertThat(awaitItem()).isEqualTo(StressLevelState(adding = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(StressLevelState(adding = UiState.Error("Fail to add stress level record")))

                cancelAndIgnoreRemainingEvents()
            }
        }
}