package com.joohnq.moodapp.viewmodel

import app.cash.turbine.test
import com.joohnq.moodapp.state.UiState
import com.joohnq.moodapp.data.repository.StatsRepository
import com.joohnq.moodapp.domain.StatsRecord
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

class StatsViewModelTest {
    private lateinit var statsRepository: StatsRepository
    private lateinit var statsViewModel: StatsViewModel

    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        statsRepository = mock()
        statsViewModel = StatsViewModel(statsRepository, dispatcher)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun mockList(count: Int): List<StatsRecord> {
        val list = mutableListOf<StatsRecord>()
        for (i in 0..count) {
            list.add(StatsRecord.init())
        }
        return list
    }

    @Test
    fun `test getStatsRecords with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val items = mockList(10)
            everySuspend { statsRepository.getStats() } returns items

            statsViewModel.statsState.test {
                assertThat(awaitItem()).isEqualTo(StatsState())

                statsViewModel.onAction(StatsIntent.GetStatsRecords)

                assertThat(awaitItem()).isEqualTo(StatsState(statsRecords = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    StatsState(
                        statsRecords = UiState.Success(items)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test getStatsRecords with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            val error = "Something went wrong"
            everySuspend { statsRepository.getStats() } throws Exception(error)

            statsViewModel.statsState.test {
                assertThat(awaitItem()).isEqualTo(StatsState())

                statsViewModel.onAction(StatsIntent.GetStatsRecords)

                assertThat(awaitItem()).isEqualTo(StatsState(statsRecords = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    StatsState(
                        statsRecords = UiState.Error(error)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test addStatsRecord with a success execution - should set Loading Success`() = runTest {
        //GIVEN
        val item = StatsRecord.init()
        everySuspend { statsRepository.addStats(any<StatsRecord>()) } returns true

        statsViewModel.statsState.test {
            assertThat(awaitItem()).isEqualTo(StatsState())

            statsViewModel.onAction(StatsIntent.AddStatsRecord(item))

            assertThat(awaitItem()).isEqualTo(StatsState(adding = UiState.Loading))
            assertThat(awaitItem()).isEqualTo(StatsState(adding = UiState.Success(true)))

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test addStatsRecord with a failed execution - should set Loading Error`() = runTest {
        //GIVEN
        val item = StatsRecord.init()
        everySuspend { statsRepository.addStats(any<StatsRecord>()) } returns false

        statsViewModel.statsState.test {
            assertThat(awaitItem()).isEqualTo(StatsState())

            statsViewModel.onAction(StatsIntent.AddStatsRecord(item))

            assertThat(awaitItem()).isEqualTo(StatsState(adding = UiState.Loading))
            assertThat(awaitItem()).isEqualTo(StatsState(adding = UiState.Error("Fail to add stats record")))

            cancelAndIgnoreRemainingEvents()
        }
    }
}