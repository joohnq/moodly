package com.joohnq.stress_level.ui.viewmodel

import app.cash.turbine.test
import com.joohnq.ui.entity.UiState
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.repository.StressLevelRepository
import com.joohnq.stress_level.domain.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.domain.use_case.GetStressLevelsUseCase
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class StressLevelViewModelTest {
    private lateinit var viewModel: StressLevelViewModel
    private lateinit var repository: StressLevelRepository
    private lateinit var addStressLevelUseCase: AddStressLevelUseCase
    private lateinit var getStressLevelsUseCase: GetStressLevelsUseCase

    @BeforeTest
    fun setUp() {
        repository = mock(MockMode.autofill)
        addStressLevelUseCase = AddStressLevelUseCase(repository)
        getStressLevelsUseCase = GetStressLevelsUseCase(repository)
        viewModel = StressLevelViewModel(
            addStressLevelUseCase = addStressLevelUseCase,
            getStressLevelsUseCase = getStressLevelsUseCase,
        )
    }

    @Test
    fun `testing getStressLevels with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getRecords() } returns Result.success(items)

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)

                viewModel.onAction(StressLevelIntent.GetAll)

                assertThat(awaitItem().records).isEqualTo(UiState.Loading)
                assertThat(awaitItem().records).isEqualTo(UiState.Success(items))
            }
        }

    @Test
    fun `testing getStressLevels with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.getRecords() } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)

                viewModel.onAction(StressLevelIntent.GetAll)

                assertThat(awaitItem().records).isEqualTo(UiState.Loading)
                assertThat(awaitItem().records).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing addStressLevel with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.addRecord(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)
                viewModel.onAction(StressLevelIntent.Add(items[0]))
                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing addStressLevel with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.addRecord(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)

                viewModel.onAction(StressLevelIntent.Add(items[0]))

                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Error(exception))
            }
        }

    companion object {
        val items = listOf(
            StressLevelRecord(
                id = 1,
                stressLevel = StressLevel.Two
            ),
            StressLevelRecord(
                id = 2,
                stressLevel = StressLevel.Three
            )
        )
    }
}