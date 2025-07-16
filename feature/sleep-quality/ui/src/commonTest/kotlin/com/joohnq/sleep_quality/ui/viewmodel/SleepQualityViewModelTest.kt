package com.joohnq.sleep_quality.ui.viewmodel

import app.cash.turbine.test
import com.joohnq.ui.entity.UiState
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
import kotlinx.coroutines.test.runTest
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
        runTest {
            everySuspend { repository.getSleepQualities() } returns Result.success(items)

            viewModel.state.test {
                assertState(field = { it.records }, UiState.Idle)
                viewModel.onAction(SleepQualityIntent.GetAll)
                assertState(field = { it.records }, UiState.Loading)
                assertState(field = { it.records }, UiState.Success(items.toResource()))
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `testing get with a failed operation - returning a Result failure with exception`() =
        runTest {
            val exception = "Something went wrong"
            everySuspend { repository.getSleepQualities() } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertState(field = { it.records }, UiState.Idle)
                viewModel.onAction(SleepQualityIntent.GetAll)
                assertState(field = { it.records }, UiState.Loading)
                assertState(field = { it.records }, UiState.Error(exception))
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `testing add with a success operation - returning a Result success with items`() =
        runTest {
            everySuspend { repository.addSleepQuality(any()) } returns Result.success(true)

            viewModel.onAction(SleepQualityIntent.Add(items[0]))

            viewModel.sideEffect.test {
                assertSideEffect(SleepQualitySideEffect.SleepQualityAdded)
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `testing add with a failed operation - returning a Result failure with exception`() =
        runTest {
            val exception = "Something went wrong"
            everySuspend { repository.addSleepQuality(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.onAction(SleepQualityIntent.Add(items[0]))

            viewModel.sideEffect.test {
                assertSideEffect(SleepQualitySideEffect.ShowError(exception))
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `testing delete with a success operation - returning a Result success`() =
        runTest {
            everySuspend { repository.deleteSleepQuality(any()) } returns Result.success(true)

            viewModel.onAction(SleepQualityIntent.Delete(1))

            viewModel.sideEffect.test {
                assertSideEffect(SleepQualitySideEffect.Deleted)
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `testing delete with a failed operation - returning a Result failure with exception`() =
        runTest {
            val exception = "Something went wrong"
            everySuspend { repository.deleteSleepQuality(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.onAction(SleepQualityIntent.Delete(1))

            viewModel.sideEffect.test {
                assertThat(awaitItem()).isEqualTo(SleepQualitySideEffect.ShowError(exception))
                cancelAndConsumeRemainingEvents()
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