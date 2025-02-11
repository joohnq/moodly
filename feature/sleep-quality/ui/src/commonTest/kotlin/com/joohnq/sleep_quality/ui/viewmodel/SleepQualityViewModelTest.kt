package com.joohnq.sleep_quality.ui.viewmodel

import app.cash.turbine.test
import com.joohnq.domain.entity.UiState
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import com.joohnq.sleep_quality.domain.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.GetSleepQualitiesUseCase
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
    private lateinit var addSleepQualityUseCase: AddSleepQualityUseCase
    private lateinit var getSleepQualitiesUseCase: GetSleepQualitiesUseCase

    @BeforeTest
    fun setUp() {
        repository = mock(MockMode.autofill)
        addSleepQualityUseCase = AddSleepQualityUseCase(repository)
        getSleepQualitiesUseCase = GetSleepQualitiesUseCase(repository)
        viewModel = SleepQualityViewModel(
            addSleepQualityUseCase = addSleepQualityUseCase,
            getSleepQualitiesUseCase = getSleepQualitiesUseCase,
        )
    }

    @Test
    fun `testing getSleepQualities with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getSleepQualities() } returns Result.success(items)

            viewModel.state.test {
                assertThat(awaitItem().sleepQualityRecords).isEqualTo(UiState.Idle)
                viewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)
                assertThat(awaitItem().sleepQualityRecords).isEqualTo(UiState.Loading)
                assertThat(awaitItem().sleepQualityRecords).isEqualTo(UiState.Success(items))
            }
        }

    @Test
    fun `testing getSleepQualities with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.getSleepQualities() } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().sleepQualityRecords).isEqualTo(UiState.Idle)

                viewModel.onAction(SleepQualityIntent.GetSleepQualityRecords)

                assertThat(awaitItem().sleepQualityRecords).isEqualTo(UiState.Loading)
                assertThat(awaitItem().sleepQualityRecords).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing addSleepQuality with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.addSleepQuality(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)
                viewModel.onAction(SleepQualityIntent.AddSleepQualityRecord(items[0]))
                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing addSleepQuality with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.addSleepQuality(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)

                viewModel.onAction(SleepQualityIntent.AddSleepQualityRecord(items[0]))

                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Error(exception))
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