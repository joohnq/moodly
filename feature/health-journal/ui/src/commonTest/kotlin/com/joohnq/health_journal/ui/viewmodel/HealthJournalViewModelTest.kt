package com.joohnq.health_journal.ui.viewmodel

import app.cash.turbine.test
import com.joohnq.core.ui.entity.UiState
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.health_journal.domain.use_case.AddHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.DeleteHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.UpdateHealthJournalsUseCase
import com.joohnq.mood.domain.entity.Mood
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class HealthJournalViewModelTest {
    private lateinit var viewModel: HealthJournalViewModel
    private lateinit var repository: HealthJournalRepository
    private lateinit var getHealthJournalsUseCase: GetHealthJournalsUseCase
    private lateinit var deleteHealthJournalsUseCase: DeleteHealthJournalsUseCase
    private lateinit var updateHealthJournalsUseCase: UpdateHealthJournalsUseCase
    private lateinit var addHealthJournalsUseCase: AddHealthJournalsUseCase

    @BeforeTest
    fun setUp() {
        repository = mock(MockMode.autofill)
        getHealthJournalsUseCase = GetHealthJournalsUseCase(repository)
        deleteHealthJournalsUseCase = DeleteHealthJournalsUseCase(repository)
        updateHealthJournalsUseCase = UpdateHealthJournalsUseCase(repository)
        addHealthJournalsUseCase = AddHealthJournalsUseCase(repository)
        viewModel = HealthJournalViewModel(
            getHealthJournalsUseCase = getHealthJournalsUseCase,
            deleteHealthJournalsUseCase = deleteHealthJournalsUseCase,
            updateHealthJournalsUseCase = updateHealthJournalsUseCase,
            addHealthJournalsUseCase = addHealthJournalsUseCase,
        )
    }

    @Test
    fun `testing getHealthJournals with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getHealthJournals() } returns Result.success(items)

            viewModel.state.test {
                assertThat(awaitItem().healthJournalRecords).isEqualTo(UiState.Idle)
                viewModel.onAction(HealthJournalIntent.GetHealthJournals)
                assertThat(awaitItem().healthJournalRecords).isEqualTo(UiState.Loading)
                assertThat(awaitItem().healthJournalRecords).isEqualTo(UiState.Success(items))
            }
        }

    @Test
    fun `testing getHealthJournals with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.getHealthJournals() } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().healthJournalRecords).isEqualTo(UiState.Idle)
                viewModel.onAction(HealthJournalIntent.GetHealthJournals)
                assertThat(awaitItem().healthJournalRecords).isEqualTo(UiState.Loading)
                assertThat(awaitItem().healthJournalRecords).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing addHealthJournal with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.addHealthJournal(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)

                viewModel.onAction(HealthJournalIntent.AddHealthJournal(items[0]))

                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing addHealthJournal with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.addHealthJournal(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)

                viewModel.onAction(HealthJournalIntent.AddHealthJournal(items[0]))

                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing updateHealthJournal with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.updateHealthJournal(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().editing).isEqualTo(UiState.Idle)

                viewModel.onAction(HealthJournalIntent.UpdateHealthJournal(items[0]))

                assertThat(awaitItem().editing).isEqualTo(UiState.Loading)
                assertThat(awaitItem().editing).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing updateHealthJournal with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.updateHealthJournal(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.state.test {
                assertThat(awaitItem().editing).isEqualTo(UiState.Idle)

                viewModel.onAction(HealthJournalIntent.UpdateHealthJournal(items[0]))

                assertThat(awaitItem().editing).isEqualTo(UiState.Loading)
                assertThat(awaitItem().editing).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing deleteHealthJournal with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.deleteHealthJournal(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().deleting).isEqualTo(UiState.Idle)

                viewModel.onAction(HealthJournalIntent.DeleteHealthJournal(1))

                assertThat(awaitItem().deleting).isEqualTo(UiState.Loading)
                assertThat(awaitItem().deleting).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing deleteHealthJournal with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.deleteHealthJournal(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.state.test {
                assertThat(awaitItem().deleting).isEqualTo(UiState.Idle)

                viewModel.onAction(HealthJournalIntent.DeleteHealthJournal(1))

                assertThat(awaitItem().deleting).isEqualTo(UiState.Loading)
                assertThat(awaitItem().deleting).isEqualTo(UiState.Error(exception))
            }
        }

    companion object {
        val items = listOf(
            HealthJournalRecord(
                id = 1,
                mood = Mood.Overjoyed
            ),
            HealthJournalRecord(
                id = 2,
                mood = Mood.Happy
            )
        )
    }
}