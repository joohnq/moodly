package com.joohnq.mood.ui.viewmodel

import app.cash.turbine.test
import com.joohnq.domain.entity.UiState
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.repository.MoodRepository
import com.joohnq.mood.domain.use_case.AddMoodUseCase
import com.joohnq.mood.domain.use_case.DeleteMoodUseCase
import com.joohnq.mood.domain.use_case.GetMoodsUseCase
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class MoodViewModelTest {
    private lateinit var viewModel: MoodViewModel
    private lateinit var repository: MoodRepository
    private lateinit var getMoodsUseCase: GetMoodsUseCase
    private lateinit var deleteMoodUseCase: DeleteMoodUseCase
    private lateinit var addMoodUseCase: AddMoodUseCase

    @BeforeTest
    fun setUp() {
        repository = mock(MockMode.autofill)
        getMoodsUseCase = mock {
            every { repository } returns repository
        }
        deleteMoodUseCase = mock {
            every { repository } returns repository
        }
        addMoodUseCase = mock {
            every { repository } returns repository
        }
        viewModel = MoodViewModel(
            getMoodsUseCase = getMoodsUseCase,
            deleteMoodUseCase = deleteMoodUseCase,
            addMoodUseCase = addMoodUseCase,
        )
    }

    @Test
    fun `testing getStats with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getMoodRecords() } returns Result.success(items)

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)
                viewModel.onAction(MoodIntent.GetAll)
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
                viewModel.onAction(MoodIntent.GetAll)
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
                viewModel.onAction(MoodIntent.Add(items[0]))
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

                viewModel.onAction(MoodIntent.Add(items[0]))

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