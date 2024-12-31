package com.joohnq.moodapp.viewmodel

import app.cash.turbine.test
import com.joohnq.moodapp.data.repository.UserPreferencesRepository
import com.joohnq.moodapp.domain.UserPreferences
import com.joohnq.moodapp.state.UiState
import com.varabyte.truthish.assertThat
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class UserPreferencesViewModelTest {
    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private lateinit var userPreferencesViewModel: UserPreferenceViewModel

    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        userPreferencesRepository = mock()
        userPreferencesViewModel = UserPreferenceViewModel(userPreferencesRepository, dispatcher)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getUserPreferences with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val item = UserPreferences.init()
            everySuspend { userPreferencesRepository.getUserPreferences() } returns item

            userPreferencesViewModel.userPreferencesState.test {
                assertThat(awaitItem()).isEqualTo(UserPreferenceState())

                userPreferencesViewModel.onAction(UserPreferenceIntent.GetUserPreferences)

                assertThat(awaitItem()).isEqualTo(UserPreferenceState(userPreferences = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserPreferenceState(
                        userPreferences = UiState.Success(item)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test getUserPreferences with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            val error = "Something went wrong"
            everySuspend { userPreferencesRepository.getUserPreferences() } throws Exception(error)

            userPreferencesViewModel.userPreferencesState.test {
                assertThat(awaitItem()).isEqualTo(UserPreferenceState())

                userPreferencesViewModel.onAction(UserPreferenceIntent.GetUserPreferences)

                assertThat(awaitItem()).isEqualTo(UserPreferenceState(userPreferences = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserPreferenceState(
                        userPreferences = UiState.Error(error)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test addUserPreferences with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            everySuspend { userPreferencesRepository.addUserPreferences(any<UserPreferences>()) } returns true

            userPreferencesViewModel.userPreferencesState.test {
                assertThat(awaitItem()).isEqualTo(UserPreferenceState())

                userPreferencesViewModel.onAction(UserPreferenceIntent.AddUserPreferences)

                assertThat(awaitItem()).isEqualTo(UserPreferenceState(adding = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserPreferenceState(
                        adding = UiState.Success(true)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test addUserPreferences with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            everySuspend { userPreferencesRepository.addUserPreferences(any<UserPreferences>()) } returns false

            userPreferencesViewModel.userPreferencesState.test {
                assertThat(awaitItem()).isEqualTo(UserPreferenceState())

                userPreferencesViewModel.onAction(UserPreferenceIntent.AddUserPreferences)

                assertThat(awaitItem()).isEqualTo(UserPreferenceState(adding = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserPreferenceState(
                        adding = UiState.Error("Failure add mood preferences")
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }
}