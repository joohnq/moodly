package com.joohnq.user.ui.viewmodel.user_preferences

import app.cash.turbine.test
import com.joohnq.core.ui.entity.UiState
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.domain.repository.UserPreferencesRepository
import com.joohnq.domain.use_case.user_preferences.AddUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.GetUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipAuthUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipOnboardingUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipWelcomeUseCase
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class UserPreferencesViewModelTest {
    private lateinit var viewModel: UserPreferencesViewModel
    private lateinit var repository: UserPreferencesRepository
    private lateinit var getUserPreferencesUseCase: GetUserPreferencesUseCase
    private lateinit var addUserPreferencesUseCase: AddUserPreferencesUseCase
    private lateinit var updateSkipWelcomeUseCase: UpdateSkipWelcomeUseCase
    private lateinit var updateSkipOnboardingUseCase: UpdateSkipOnboardingUseCase
    private lateinit var updateSkipAuthUseCase: UpdateSkipAuthUseCase

    @BeforeTest
    fun setUp() {
        repository = mock(MockMode.autofill)
        getUserPreferencesUseCase = GetUserPreferencesUseCase(repository)
        addUserPreferencesUseCase = AddUserPreferencesUseCase(repository)
        updateSkipWelcomeUseCase = UpdateSkipWelcomeUseCase(repository)
        updateSkipOnboardingUseCase = UpdateSkipOnboardingUseCase(repository)
        updateSkipAuthUseCase = UpdateSkipAuthUseCase(repository)
        viewModel = UserPreferencesViewModel(
            getUserPreferencesUseCase = getUserPreferencesUseCase,
            addUserPreferencesUseCase = addUserPreferencesUseCase,
            updateSkipWelcomeUseCase = updateSkipWelcomeUseCase,
            updateSkipOnboardingUseCase = updateSkipOnboardingUseCase,
            updateSkipAuthUseCase = updateSkipAuthUseCase
        )
    }

    @Test
    fun `testing getUserPreferences with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getUserPreferences() } returns Result.success(UserPreferences())

            viewModel.state.test {
                assertThat(awaitItem().userPreferences).isEqualTo(UiState.Idle)

                viewModel.onAction(UserPreferenceViewModelIntent.GetUserPreferences)

                assertThat(awaitItem().userPreferences).isEqualTo(UiState.Loading)
                assertThat(awaitItem().userPreferences).isEqualTo(UiState.Success(UserPreferences()))
            }
        }

    @Test
    fun `testing getUserPreferences with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.getUserPreferences() } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().userPreferences).isEqualTo(UiState.Idle)

                viewModel.onAction(UserPreferenceViewModelIntent.GetUserPreferences)

                assertThat(awaitItem().userPreferences).isEqualTo(UiState.Loading)
                assertThat(awaitItem().userPreferences).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing addUserPreferences with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.addUserPreferences(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)

                viewModel.onAction(UserPreferenceViewModelIntent.AddUserPreferences)

                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing addUserPreferences with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.addUserPreferences(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().adding).isEqualTo(UiState.Idle)

                viewModel.onAction(UserPreferenceViewModelIntent.AddUserPreferences)

                assertThat(awaitItem().adding).isEqualTo(UiState.Loading)
                assertThat(awaitItem().adding).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing updateSkipWelcome with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.updateSkipWelcome(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)

                viewModel.onAction(UserPreferenceViewModelIntent.UpdateSkipWelcome(true))

                assertThat(awaitItem().updating).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing updateSkipWelcome with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.updateSkipWelcome(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)

                viewModel.onAction(UserPreferenceViewModelIntent.UpdateSkipWelcome(true))

                assertThat(awaitItem().updating).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing updateSkipOnboarding with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.updateSkipOnboarding(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)

                viewModel.onAction(UserPreferenceViewModelIntent.UpdateSkipOnboarding(true))

                assertThat(awaitItem().updating).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing updateSkipOnboarding with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.updateSkipOnboarding(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)

                viewModel.onAction(UserPreferenceViewModelIntent.UpdateSkipOnboarding(true))

                assertThat(awaitItem().updating).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing updateSkipAuth with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.updateSkipAuth(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)

                viewModel.onAction(UserPreferenceViewModelIntent.UpdateSkipAuth(true))

                assertThat(awaitItem().updating).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing updateSkipAuth with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.updateSkipAuth(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)
                viewModel.onAction(UserPreferenceViewModelIntent.UpdateSkipAuth(true))
                assertThat(awaitItem().updating).isEqualTo(UiState.Error(exception))
            }
        }
}