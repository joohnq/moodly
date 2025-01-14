package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.fake.UserRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class UpdateUserUseCaseTest {
    private val repository: UserRepositoryFake = UserRepositoryFake()
    private val getUseCase: GetUserUseCase = GetUserUseCase(repository)
    private val addUserCase: AddUserUseCase = AddUserUseCase(repository)
    private val useCase: UpdateUserUseCase =
        UpdateUserUseCase(repository)

    private suspend fun initUser() {
        addUserCase.invoke(User())
    }

    @Test
    fun `GIVEN a valid request WHEN calling use case THEN should return true`() =
        runBlocking {
            //GIVEN
            initUser()

            //WHEN
            val res = useCase.invoke(
                User(
                    name = "Name 2",
                    soughtHelp = ProfessionalHelp.Yes
                )
            ).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)

            //WHEN
            val user = getUseCase.invoke().getOrNull()

            //THEN
            assertThat(user).isNotNull()
            assertThat(user).isEqualTo(
                User(
                    name = "Name 2",
                    soughtHelp = ProfessionalHelp.Yes
                )
            )
        }

    @Test
    fun `GIVEN a invalid request WHEN calling use case THEN should return exception in failure`() =
        runBlocking {
            //GIVEN
            initUser()
            repository.updateShouldThrowError(true)

            //WHEN
            val res = useCase.invoke(
                User(
                    name = "Name 2",
                    soughtHelp = ProfessionalHelp.Yes
                )
            ).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to update user")
        }
}