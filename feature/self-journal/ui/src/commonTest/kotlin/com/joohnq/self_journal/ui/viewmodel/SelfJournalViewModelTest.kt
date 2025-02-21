package com.joohnq.self_journal.ui.viewmodel

import app.cash.turbine.test
import com.joohnq.domain.entity.UiState
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.self_journal.domain.repository.SelfJournalRepository
import com.joohnq.self_journal.domain.use_case.AddSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.UpdateSelfJournalsUseCase
import com.joohnq.self_journal.ui.mapper.toResource
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
    fun `testing get with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getSelfJournals() } returns Result.success(items)

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)
                viewModel.onAction(SelfJournalIntent.GetAll)
                assertThat(awaitItem().records).isEqualTo(UiState.Loading)
                assertThat(awaitItem().records).isEqualTo(UiState.Success(items.toResource()))
            }
        }

    @Test
    fun `testing get with a failed operation - returning a Result failure with exception`() =
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
    fun `testing add with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.addSelfJournal(any()) } returns Result.success(true)

            viewModel.onAction(SelfJournalIntent.Add(items[0]))
            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SelfJournalSideEffect.SelfJournalAdded)
            }
        }

    @Test
    fun `testing add with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.addSelfJournal(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.onAction(SelfJournalIntent.Add(items[0]))

            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SelfJournalSideEffect.ShowError(exception))
            }
        }

    @Test
    fun `testing update with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.updateSelfJournal(any()) } returns Result.success(true)

            viewModel.onAction(SelfJournalIntent.Update(items[0]))
            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SelfJournalSideEffect.Updated)
            }
        }

    @Test
    fun `testing update with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.updateSelfJournal(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.onAction(SelfJournalIntent.Update(items[0]))

            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SelfJournalSideEffect.ShowError(exception))
            }
        }

    @Test
    fun `testing delete with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.deleteSelfJournal(any()) } returns Result.success(true)

            viewModel.onAction(SelfJournalIntent.Delete(1))

            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SelfJournalSideEffect.SelfJournalDeleted)
            }
        }

    @Test
    fun `testing delete with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.deleteSelfJournal(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.onAction(SelfJournalIntent.Delete(1))

            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SelfJournalSideEffect.ShowError(exception))
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