package com.joohnq.mood.ui.viewmodel

import app.cash.turbine.test
import com.joohnq.core.ui.entity.UiState
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.repository.MoodRepository
import com.joohnq.mood.domain.use_case.AddStatsUseCase
import com.joohnq.mood.domain.use_case.DeleteStatsUseCase
import com.joohnq.mood.domain.use_case.GetStatsUseCase
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class StatsViewModelTest {
    private lateinit var viewModel: StatsViewModel
    private lateinit var repository: MoodRepository
    private lateinit var getStatsUseCase: GetStatsUseCase
    private lateinit var deleteStatsUseCase: DeleteStatsUseCase
    private lateinit var addStatsUseCase: AddStatsUseCase

    @BeforeTest
    fun setUp() {
        repository = mock(MockMode.autofill)
        getStatsUseCase = GetStatsUseCase(repository)
        deleteStatsUseCase = DeleteStatsUseCase(repository)
        addStatsUseCase = AddStatsUseCase(repository)
        viewModel = StatsViewModel(
            getStatsUseCase = getStatsUseCase,
            deleteStatsUseCase = deleteStatsUseCase,
            addStatsUseCase = addStatsUseCase,
        )
    }

    @Test
    fun `testing getStats with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getMoodRecords() } returns Result.success(items)

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)
                viewModel.onAction(StatsIntent.GetStatsRecords)
                assertThat(awaitItem().records).isEqualTo(UiState.Loading)
                assertThat(awaitItem().records).isEqualTo(UiState.Success(items))
            }
        }

    @Test
    fun `testing getStats with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.getMoodRecords() } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)
                viewModel.onAction(StatsIntent.GetStatsRecords)
                assertThat(awaitItem().records).isEqualTo(UiState.Loading)
                assertThat(awaitItem().records).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing addStats with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.addMoodRecord(any()) } returns
                    Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)
                viewModel.onAction(StatsIntent.AddStatsRecord(items[0]))
                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing addStats with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.addMoodRecord(any()) } returns
                    Result.failure(Exception(exception))

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)

                viewModel.onAction(StatsIntent.AddStatsRecord(items[0]))

                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Error(exception))
            }
        }

    companion object {
        val items = listOf(
            MoodRecord(
                id = 1,
                mood = Mood.Overjoyed
            ),
            MoodRecord(
                id = 2,
                mood = Mood.Happy
            )
        )
    }
}