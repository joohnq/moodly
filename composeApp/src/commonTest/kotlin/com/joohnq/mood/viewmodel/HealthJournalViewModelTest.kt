package com.joohnq.mood.viewmodel

import app.cash.turbine.test
import com.joohnq.mood.state.UiState
import com.joohnq.mood.data.repository.HealthJournalRepository
import com.joohnq.mood.domain.HealthJournalRecord
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

class HealthJournalViewModelTest {
    private lateinit var healthJournalRepository: HealthJournalRepository
    private lateinit var healthJournalViewModel: HealthJournalViewModel

    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        healthJournalRepository = mock()
        healthJournalViewModel = HealthJournalViewModel(healthJournalRepository, dispatcher)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun mockList(count: Int): List<HealthJournalRecord> {
        val list = mutableListOf<HealthJournalRecord>()
        for (i in 0..count) {
            list.add(HealthJournalRecord.init())
        }
        return list
    }

    @Test
    fun `test addHealthJournal with a success execution - should set Loading Success`() = runTest {
        //GIVEN
        val item = HealthJournalRecord.init()
        everySuspend { healthJournalRepository.addHealthJournal(any<HealthJournalRecord>()) } returns true

        healthJournalViewModel.healthJournalState.test {
            assertThat(awaitItem()).isEqualTo(HealthJournalState())

            healthJournalViewModel.onAction(HealthJournalIntent.AddHealthJournal(item))

            assertThat(awaitItem()).isEqualTo(HealthJournalState(adding = UiState.Loading))
            assertThat(awaitItem()).isEqualTo(HealthJournalState(adding = UiState.Success(true)))

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test addHealthJournal with a success execution - should set Loading Error`() = runTest {
        //GIVEN
        val item = HealthJournalRecord.init()
        everySuspend { healthJournalRepository.addHealthJournal(any<HealthJournalRecord>()) } returns false

        healthJournalViewModel.healthJournalState.test {
            assertThat(awaitItem()).isEqualTo(HealthJournalState())

            healthJournalViewModel.onAction(HealthJournalIntent.AddHealthJournal(item))

            assertThat(awaitItem()).isEqualTo(HealthJournalState(adding = UiState.Loading))
            assertThat(awaitItem()).isEqualTo(HealthJournalState(adding = UiState.Error("Fail to add health journal")))

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test updateHealthJournal with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val item = HealthJournalRecord.init()
            everySuspend { healthJournalRepository.updateHealthJournal(any<HealthJournalRecord>()) } returns true

            healthJournalViewModel.healthJournalState.test {
                assertThat(awaitItem()).isEqualTo(HealthJournalState())

                healthJournalViewModel.onAction(HealthJournalIntent.UpdateHealthJournal(item))

                assertThat(awaitItem()).isEqualTo(HealthJournalState(editing = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(HealthJournalState(editing = UiState.Success(true)))

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test updateHealthJournal with a success execution - should set Loading Error`() = runTest {
        //GIVEN
        val item = HealthJournalRecord.init()
        everySuspend { healthJournalRepository.updateHealthJournal(any<HealthJournalRecord>()) } returns false

        healthJournalViewModel.healthJournalState.test {
            assertThat(awaitItem()).isEqualTo(HealthJournalState())

            healthJournalViewModel.onAction(HealthJournalIntent.UpdateHealthJournal(item))

            assertThat(awaitItem()).isEqualTo(HealthJournalState(editing = UiState.Loading))
            assertThat(awaitItem()).isEqualTo(HealthJournalState(editing = UiState.Error("Fail to update health journal")))

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test getHealthJournals with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val items = mockList(10)
            everySuspend { healthJournalRepository.getHealthJournals() } returns items

            healthJournalViewModel.healthJournalState.test {
                assertThat(awaitItem()).isEqualTo(HealthJournalState())

                healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)

                assertThat(awaitItem()).isEqualTo(HealthJournalState(healthJournalRecords = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    HealthJournalState(
                        healthJournalRecords = UiState.Success(
                            items
                        )
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test getHealthJournals with a success execution - should set Loading Error`() = runTest {
        //GIVEN
        everySuspend { healthJournalRepository.getHealthJournals() } throws Exception("Something went wrong")

        healthJournalViewModel.healthJournalState.test {
            assertThat(awaitItem()).isEqualTo(HealthJournalState())

            healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)

            assertThat(awaitItem()).isEqualTo(HealthJournalState(healthJournalRecords = UiState.Loading))
            assertThat(awaitItem()).isEqualTo(
                HealthJournalState(
                    healthJournalRecords = UiState.Error(
                        "Something went wrong"
                    )
                )
            )

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test deleteHealthJournal with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val id = 1
            everySuspend { healthJournalRepository.deleteHealthJournal(any<Int>()) } returns true

            val initialValue = HealthJournalState(
                healthJournalRecords = UiState.Success(
                    mockList(10)
                )
            )

            healthJournalViewModel.healthJournalState.test {
                assertThat(awaitItem()).isEqualTo(HealthJournalState())

                healthJournalViewModel.onAction(
                    HealthJournalIntent.SetHealthJournalStateForTesting(
                        initialValue
                    )
                )
                assertThat(awaitItem()).isEqualTo(initialValue)
                healthJournalViewModel.onAction(HealthJournalIntent.DeleteHealthJournal(id))

                assertThat(awaitItem()).isEqualTo(initialValue.copy(deleting = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    initialValue.copy(
                        deleting = UiState.Success(true)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test deleteHealthJournal with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            val id = 1
            everySuspend { healthJournalRepository.deleteHealthJournal(any<Int>()) } returns false

            val initialValue = HealthJournalState(
                healthJournalRecords = UiState.Success(
                    mockList(10)
                )
            )

            healthJournalViewModel.healthJournalState.test {
                assertThat(awaitItem()).isEqualTo(HealthJournalState())

                healthJournalViewModel.onAction(
                    HealthJournalIntent.SetHealthJournalStateForTesting(
                        initialValue
                    )
                )
                assertThat(awaitItem()).isEqualTo(initialValue)
                healthJournalViewModel.onAction(HealthJournalIntent.DeleteHealthJournal(id))

                assertThat(awaitItem()).isEqualTo(initialValue.copy(deleting = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    initialValue.copy(
                        deleting = UiState.Error("Fail to delete")
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

}