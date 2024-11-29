package com.joohnq.moodapp.viewmodel

import app.cash.turbine.test
import com.joohnq.moodapp.data.repository.UserRepository
import com.joohnq.moodapp.domain.User
import com.joohnq.moodapp.ui.state.UiState
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

class UserViewModelTest {
    private lateinit var userRepository: UserRepository
    private lateinit var userViewModel: UserViewModel

    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        userRepository = mock()
        userViewModel = UserViewModel(userRepository, dispatcher)
        Dispatchers.setMain(dispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test updateUser with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val item = User.init()
            everySuspend { userRepository.updateUser(any<User>()) } returns true

            userViewModel.userState.test {
                assertThat(awaitItem()).isEqualTo(UserState())

                userViewModel.onAction(UserIntent.UpdateUser(item))

                assertThat(awaitItem()).isEqualTo(UserState(updating = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserState(
                        updating = UiState.Success(true)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test updateUser with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            everySuspend { userRepository.updateUser(any<User>()) } returns false

            userViewModel.userState.test {
                assertThat(awaitItem()).isEqualTo(UserState())

                userViewModel.onAction(UserIntent.UpdateUser(User.init()))

                assertThat(awaitItem()).isEqualTo(UserState(updating = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserState(
                        updating = UiState.Error("Failure to set user")
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test getUser with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val user = User.init()
            everySuspend { userRepository.getUser() } returns user

            userViewModel.userState.test {
                assertThat(awaitItem()).isEqualTo(UserState())

                userViewModel.onAction(UserIntent.GetUser)

                assertThat(awaitItem()).isEqualTo(UserState(user = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserState(
                        user = UiState.Success(user)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test getUser with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            val error = "Something went wrong"
            everySuspend { userRepository.getUser() } throws Exception(error)

            userViewModel.userState.test {
                assertThat(awaitItem()).isEqualTo(UserState())

                userViewModel.onAction(UserIntent.GetUser)

                assertThat(awaitItem()).isEqualTo(UserState(user = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserState(
                        user = UiState.Error(error)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test updateUserName with a success execution - should set Loading Success`() =
        runTest {
            //GIVEN
            val name = "name"
            everySuspend { userRepository.updateUserName(any<String>()) } returns true

            userViewModel.userState.test {
                assertThat(awaitItem()).isEqualTo(UserState())

                userViewModel.onAction(UserIntent.UpdateUserName(name))

                assertThat(awaitItem()).isEqualTo(UserState(updating = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserState(
                        updating = UiState.Success(true)
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `test updateUserName with a failed execution - should set Loading Error`() =
        runTest {
            //GIVEN
            val name = "name"
            everySuspend { userRepository.updateUserName(any<String>()) } returns false

            userViewModel.userState.test {
                assertThat(awaitItem()).isEqualTo(UserState())

                userViewModel.onAction(UserIntent.UpdateUserName(name))

                assertThat(awaitItem()).isEqualTo(UserState(updating = UiState.Loading))
                assertThat(awaitItem()).isEqualTo(
                    UserState(
                        updating = UiState.Error("Failure to set user")
                    )
                )

                cancelAndIgnoreRemainingEvents()
            }
        }
}