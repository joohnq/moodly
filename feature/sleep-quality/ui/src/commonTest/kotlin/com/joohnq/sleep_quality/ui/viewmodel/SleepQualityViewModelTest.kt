package com.joohnq.sleep_quality.ui.viewmodel

import app.cash.turbine.test
import com.joohnq.domain.entity.UiState
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import com.joohnq.sleep_quality.domain.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class SleepQualityViewModelTest {
    private lateinit var viewModel: SleepQualityViewModel
    private lateinit var repository: SleepQualityRepository

    @BeforeTest
    fun setUp() {
        repository = mock(MockMode.autofill)
        val addSleepQualityUseCase = AddSleepQualityUseCase(repository)
        val getSleepQualitiesUseCase = GetSleepQualitiesUseCase(repository)
        val deleteSleepQualityUseCase = DeleteSleepQualityUseCase(repository)
        viewModel = SleepQualityViewModel(
            addSleepQualityUseCase = addSleepQualityUseCase,
            getSleepQualitiesUseCase = getSleepQualitiesUseCase,
            deleteSleepQualityUseCase = deleteSleepQualityUseCase
        )
    }

    @Test
    fun `testing get with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getSleepQualities() } returns Result.success(items)

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)
                viewModel.onAction(SleepQualityIntent.GetAll)
                assertThat(awaitItem().records).isEqualTo(UiState.Loading)
                assertThat(awaitItem().records).isEqualTo(UiState.Success(items.toResource()))
            }
        }

    @Test
    fun `testing get with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.getSleepQualities() } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().records).isEqualTo(UiState.Idle)

                viewModel.onAction(SleepQualityIntent.GetAll)

                assertThat(awaitItem().records).isEqualTo(UiState.Loading)
                assertThat(awaitItem().records).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing add with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.addSleepQuality(any()) } returns Result.success(true)

            viewModel.onAction(SleepQualityIntent.Add(items[0]))
            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SleepQualitySideEffect.SleepQualityAdded)
            }
        }

    @Test
    fun `testing add with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.addSleepQuality(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.onAction(SleepQualityIntent.Add(items[0]))

            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SleepQualitySideEffect.ShowError(exception))
            }
        }

    @Test
    fun `testing delete with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.deleteSleepQuality(any()) } returns Result.success(true)

            viewModel.onAction(SleepQualityIntent.Delete(1))
            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SleepQualitySideEffect.Deleted)
            }
        }

    @Test
    fun `testing delete with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.deleteSleepQuality(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.onAction(SleepQualityIntent.Delete(1))

            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SleepQualitySideEffect.ShowError(exception))
            }
        }

    companion object {
        val items = listOf(
            SleepQualityRecord(
                id = 1,
                sleepQuality = SleepQuality.Fair
            ),
            SleepQualityRecord(
                id = 2,
                sleepQuality = SleepQuality.Good
            )
        )
    }
}