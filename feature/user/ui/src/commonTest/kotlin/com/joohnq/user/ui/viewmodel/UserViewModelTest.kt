package com.joohnq.user.ui.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import app.cash.turbine.test
import com.joohnq.ui.entity.UiState
import com.joohnq.domain.entity.User
import com.joohnq.domain.repository.UserRepository
import com.joohnq.domain.use_case.*
import com.joohnq.storage.api.FileStorage
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class UserViewModelTest {
    private lateinit var viewModel: UserViewModel
    private lateinit var fileStorage: FileStorage
    private lateinit var repository: UserRepository
    private lateinit var updateUserUseCase: UpdateUserUseCase
    private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var updateUserNameUseCase: UpdateUserNameUseCase
    private lateinit var updateUserImageBitmapUseCase: UpdateUserImageBitmapUseCase
    private lateinit var updateUserImageDrawableUseCase: UpdateUserImageDrawableUseCase

    @BeforeTest
    fun setUp() {
        fileStorage = mock(MockMode.autofill)
        repository = mock(MockMode.autofill)

        updateUserUseCase = UpdateUserUseCase(repository)
        getUserUseCase = GetUserUseCase(repository)
        updateUserNameUseCase = UpdateUserNameUseCase(repository)
        updateUserImageBitmapUseCase =
            UpdateUserImageBitmapUseCase(fileStorage = fileStorage, userRepository = repository)
        updateUserImageDrawableUseCase = UpdateUserImageDrawableUseCase(repository)
        viewModel = UserViewModel(
            updateUserUseCase = updateUserUseCase,
            getUserUseCase = getUserUseCase,
            updateUserNameUseCase = updateUserNameUseCase,
            updateUserImageBitmapUseCase = updateUserImageBitmapUseCase,
            updateUserImageDrawableUseCase = updateUserImageDrawableUseCase
        )
    }

    @Test
    fun `testing getUser with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.getUser() } returns Result.success(
                User()
            )

            viewModel.state.test {
                assertThat(awaitItem().user).isEqualTo(UiState.Idle)
                viewModel.onAction(UserIntent.GetUser)
                assertThat(awaitItem().user).isEqualTo(UiState.Loading)
                awaitItem().user assertThatContains {
                    it is UiState.Success && it.data.id == 1 && it.data.name == "" && it.data.image == null
                }
            }
        }

    @Test
    fun `testing getUser with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.getUser() } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().user).isEqualTo(UiState.Idle)
                viewModel.onAction(UserIntent.GetUser)
                assertThat(awaitItem().user).isEqualTo(UiState.Loading)
                assertThat(awaitItem().user).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing updateUser with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.updateUser(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)
                viewModel.onAction(UserIntent.UpdateUser(User(name = "JH")))
                assertThat(awaitItem().updating).isEqualTo(UiState.Loading)
                assertThat(awaitItem().updating).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing updateUser with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.updateUser(any()) } returns Result.failure(
                Exception(
                    exception
                )
            )

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)
                viewModel.onAction(UserIntent.UpdateUser(User(name = "JH")))
                assertThat(awaitItem().updating).isEqualTo(UiState.Loading)
                assertThat(awaitItem().updating).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing updateUserImageBitmap with a success operation - returning a Result success with items`() =
        runBlocking {
            val bitmap: ImageBitmap = mock(MockMode.autofill)
            everySuspend { fileStorage.saveImage(any(), any(), any()) } returns "path"
            everySuspend { repository.updateUserImage(any(), any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)
                viewModel.onAction(
                    UserIntent.UpdateUserImageBitmap(
                        bitmap
                    )
                )
                assertThat(awaitItem().updating).isEqualTo(UiState.Loading)
                assertThat(awaitItem().updating).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing updateUserImageBitmap with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val bitmap: ImageBitmap = mock(MockMode.autofill)
            val exception = "Something went wrong"
            everySuspend { fileStorage.saveImage(any(), any(), any()) } throws Exception(exception)

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)
                viewModel.onAction(UserIntent.UpdateUserImageBitmap(bitmap))
                assertThat(awaitItem().updating).isEqualTo(UiState.Loading)
                assertThat(awaitItem().updating).isEqualTo(UiState.Error("Error to save image"))
            }
        }

    @Test
    fun `testing updateUserImageDrawable with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.updateUserImage(any(), any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)
                viewModel.onAction(
                    UserIntent.UpdateUserImageDrawable(0)
                )
                assertThat(awaitItem().updating).isEqualTo(UiState.Loading)
                assertThat(awaitItem().updating).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing updateUserImageDrawable with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.updateUserImage(any(), any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)
                viewModel.onAction(UserIntent.UpdateUserImageDrawable(0))
                assertThat(awaitItem().updating).isEqualTo(UiState.Loading)
                assertThat(awaitItem().updating).isEqualTo(UiState.Error(exception))
            }
        }

    @Test
    fun `testing updateUserName with a success operation - returning a Result success with items`() =
        runBlocking {
            everySuspend { repository.updateUserName(any()) } returns Result.success(true)

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)
                viewModel.onAction(
                    UserIntent.UpdateUserName("name")
                )
                assertThat(awaitItem().updating).isEqualTo(UiState.Loading)
                assertThat(awaitItem().updating).isEqualTo(UiState.Success(true))
            }
        }

    @Test
    fun `testing updateUserName with a failed operation - returning a Result failure with exception`() =
        runBlocking {
            val exception = "Something went wrong"
            everySuspend { repository.updateUserName(any()) } returns Result.failure(
                Exception(exception)
            )

            viewModel.state.test {
                assertThat(awaitItem().updating).isEqualTo(UiState.Idle)
                viewModel.onAction(UserIntent.UpdateUserName("name"))
                assertThat(awaitItem().updating).isEqualTo(UiState.Loading)
                assertThat(awaitItem().updating).isEqualTo(UiState.Error(exception))
            }
        }
}