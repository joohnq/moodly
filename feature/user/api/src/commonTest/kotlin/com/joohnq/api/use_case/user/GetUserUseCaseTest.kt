package com.joohnq.api.use_case.user

import com.joohnq.core.test.assertThatContains
import com.joohnq.api.entity.User
import com.joohnq.api.fake.UserRepositoryFake
import com.joohnq.api.use_case.AddUserUseCase
import com.joohnq.api.use_case.GetUserUseCase
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class GetUserUseCaseTest {
    private val repository: UserRepositoryFake = UserRepositoryFake()
    private val addUserCase: AddUserUseCase = AddUserUseCase(repository)
    private val useCase: GetUserUseCase = GetUserUseCase(repository)

    private suspend fun initUser() {
        addUserCase.invoke(User())
    }

    @Test
    fun `GIVEN a valid request WHEN calling use case THEN should return true`() =
        runBlocking {
            //GIVEN
            initUser()

            //WHEN
            val user = useCase.invoke().getOrNull()

            //THEN
            user assertThatContains {
                it?.id == 1 && it.image == null && it.name == ""
            }
        }

    @Test
    fun `GIVEN a invalid request WHEN calling use case THEN should return exception in failure`() =
        runBlocking {
            //GIVEN
            initUser()
            repository.updateShouldThrowError(true)

            //WHEN
            val res = useCase.invoke().exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to get user")
        }
}