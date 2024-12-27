package com.joohnq.mood.viewmodel

import app.cash.turbine.test
import com.joohnq.mood.state.UiState
import com.joohnq.mood.data.repository.SleepQualityRepository
import com.joohnq.mood.domain.SleepQualityRecord
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

class SleepQualityViewModelTest {
    private lateinit var sleepQualityRepository: SleepQualityRepository
    private lateinit var sleepQualityViewModel: SleepQualityViewModel

    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        sleepQualityRepository = mock()
        sleepQualityViewModel = SleepQualityViewModel(sleepQualityRepository, dispatcher)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun mockList(count: Int): List<SleepQualityRecord> {
        val list = mutableListOf<SleepQualityRecord>()
        for (i in 0..count) {
            list.add(SleepQualityRecord.init())
        }
        return list
    }

    @Test
    fun `test getSleepQualityRecords with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val items = mockList(10)
            everySuspend { sleepQualityRepository.getSleepQualities() } returns items

            sleepQualityViewModel.sleepQualityState.test {
                assertThat(awaitItem()).isEqualTo(SleepQualityState())

                sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)

                assertThat(awaitItem()).isEqualTo(SleepQualityState(sleepQualityRecords = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    SleepQualityState(
                        sleepQualityRecords = UiState.Success(items)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test getSleepQualityRecords with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            val error = "Something went wrong"
            everySuspend { sleepQualityRepository.getSleepQualities() } throws Exception(error)

            sleepQualityViewModel.sleepQualityState.test {
                assertThat(awaitItem()).isEqualTo(SleepQualityState())

                sleepQualityViewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)

                assertThat(awaitItem()).isEqualTo(SleepQualityState(sleepQualityRecords = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    SleepQualityState(
                        sleepQualityRecords = UiState.Error(error)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }


    @Test
    fun `test addHealthJournal with a success execution - should set Loading Success`() = runTest {
        //GIVEN
        val item = SleepQualityRecord.init()
        everySuspend { sleepQualityRepository.addSleepQuality(any<SleepQualityRecord>()) } returns true

        sleepQualityViewModel.sleepQualityState.test {
            assertThat(awaitItem()).isEqualTo(SleepQualityState())

            sleepQualityViewModel.onAction(SleepQualityIntent.AddSleepQualityRecord(item))

            assertThat(awaitItem()).isEqualTo(SleepQualityState(adding = UiState.Loading))
            assertThat(awaitItem()).isEqualTo(SleepQualityState(adding = UiState.Success(true)))

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test addHealthJournal with a failed execution - should set Loading Error`() = runTest {
        //GIVEN
        val item = SleepQualityRecord.init()
        everySuspend { sleepQualityRepository.addSleepQuality(any<SleepQualityRecord>()) } returns false

        sleepQualityViewModel.sleepQualityState.test {
            assertThat(awaitItem()).isEqualTo(SleepQualityState())

            sleepQualityViewModel.onAction(SleepQualityIntent.AddSleepQualityRecord(item))

            assertThat(awaitItem()).isEqualTo(SleepQualityState(adding = UiState.Loading))
            assertThat(awaitItem()).isEqualTo(SleepQualityState(adding = UiState.Error("Fail to add sleep quality record")))

            cancelAndIgnoreRemainingEvents()
        }
    }
}