package com.joohnq.domain.use_case.user

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.fake.UserRepositoryFake
import com.joohnq.domain.use_case.AddUserUseCase
import com.joohnq.domain.use_case.GetUserUseCase
import com.joohnq.domain.use_case.UpdateUserImageBitmapUseCase
import com.joohnq.storage.api.FileStorage
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class UpdateUserImageBitmapUseCaseTest {
    private val repository: UserRepositoryFake = UserRepositoryFake()
    private val getUseCase: GetUserUseCase = GetUserUseCase(repository)
    private val addUserCase: AddUserUseCase = AddUserUseCase(repository)
    private lateinit var fileStorage: FileStorage
    private val useCase: UpdateUserImageBitmapUseCase =
        UpdateUserImageBitmapUseCase(fileStorage, repository)

    @BeforeTest
    fun setUp() {
        fileStorage = mock(MockMode.autoUnit) {
            everySuspend { saveImage(any(), any(), any()) } returns "path"
        }
    }

    private suspend fun initUser() {
        addUserCase.invoke(User())
    }

    @Test
    fun `GIVEN a valid request WHEN calling use case THEN should return true`() =
        runBlocking {
            //GIVEN
            initUser()

            //WHEN
            val imageBitmap = mock<ImageBitmap>()
            val res = useCase.invoke(imageBitmap).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)

            //WHEN
            val user = getUseCase.invoke().getOrNull()

            //THEN
            assertThat(user).isNotNull()
            assertThat(user).isEqualTo(
                User(image = "path")
            )
        }

    @Test
    fun `GIVEN a invalid request WHEN calling use case THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(
                ProfessionalHelp.Yes
            ).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to update user sought help")
        }
}