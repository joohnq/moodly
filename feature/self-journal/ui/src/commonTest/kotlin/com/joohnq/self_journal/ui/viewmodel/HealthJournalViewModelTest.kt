package com.joohnq.self_journal.ui.viewmodel

import app.cash.turbine.test
import com.joohnq.core.ui.entity.UiState
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.repository.SelfJournalRepository
import com.joohnq.self_journal.domain.use_case.AddSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.UpdateSelfJournalsUseCase
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class SelfJournalViewModelTest {
    private lateinit var viewModel: SelfJournalViewModel
    private lateinit var repository: SelfJournalRepository
    private lateinit var getSelfJournalsUseCase: GetSelfJournalsUseCase
    private lateinit var deleteSelfJournalsUseCase: DeleteSelfJournalsUseCase
    private lateinit var updateSelfJournalsUseCase: UpdateSelfJournalsUseCase
    private lateinit var addSelfJournalsUseCase: AddSelfJournalsUseCase

    @BeforeTest
    fun setUp() {
        repository = mock(MockMode.autofill)
        getSelfJournalsUseCase = GetSelfJournalsUseCase(repository)
        deleteSelfJournalsUseCase = DeleteSelfJournalsUseCase(repository)
        updateSelfJournalsUseCase = UpdateSelfJournalsUseCase(repository)
        addSelfJournalsUseCase = AddSelfJournalsUseCase(repository)
        viewModel = SelfJournalViewModel(
            getSelfJournalsUseCase = getSelfJournalsUseCase,
            deleteSelfJournalsUseCase = deleteSelfJournalsUseCase,
            updateSelfJournalsUseCase = updateSelfJournalsUseCase,
            addSelfJournalsUseCase = addSelfJournalsUseCase,
        )
    }

    @Test
    fun `testing getSelfJournals with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getSelfJournals() } returns Result.success(items)

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)
                viewModel.onAction(SelfJournalIntent.GetAll)
                assertThat(awaitItem().records).isEqualTo(UiState.Loading)
                assertThat(awaitItem().records).isEqualTo(UiState.Success(items))
            }
        }

    @Test
    fun `testing getSelfJournals with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.getSelfJournals() } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)
                viewModel.onAction(SelfJournalIntent.GetAll)
                assertThat(awaitItem().records).isEqualTo(UiState.Loading)
                assertThat(awaitItem().records).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing addSelfJournal with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.addSelfJournal(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)

                viewModel.onAction(SelfJournalIntent.Add(items[0]))

                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing addSelfJournal with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.addSelfJournal(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)

                viewModel.onAction(SelfJournalIntent.Add(items[0]))

                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing updateSelfJournal with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.updateSelfJournal(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().editing).isEqualTo(UiState.Idle)

                viewModel.onAction(SelfJournalIntent.Update(items[0]))

                assertThat(awaitItem().editing).isEqualTo(UiState.Loading)
                assertThat(awaitItem().editing).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing updateSelfJournal with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.updateSelfJournal(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.state.test {
                assertThat(awaitItem().editing).isEqualTo(UiState.Idle)

                viewModel.onAction(SelfJournalIntent.Update(items[0]))

                assertThat(awaitItem().editing).isEqualTo(UiState.Loading)
                assertThat(awaitItem().editing).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing deleteSelfJournal with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.deleteSelfJournal(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().deleting).isEqualTo(UiState.Idle)

                viewModel.onAction(SelfJournalIntent.Delete(1))

                assertThat(awaitItem().deleting).isEqualTo(UiState.Loading)
                assertThat(awaitItem().deleting).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing deleteSelfJournal with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.deleteSelfJournal(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.state.test {
                assertThat(awaitItem().deleting).isEqualTo(UiState.Idle)

                viewModel.onAction(SelfJournalIntent.Delete(1))

                assertThat(awaitItem().deleting).isEqualTo(UiState.Loading)
                assertThat(awaitItem().deleting).isEqualTo(UiState.Error(exception))
            }
        }

    companion object {
        val items = listOf(
            SelfJournalRecord(
                id = 1,
                mood = Mood.Overjoyed
            ),
            SelfJournalRecord(
                id = 2,
                mood = Mood.Happy
            )
        )
    }
}