package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.User
import com.joohnq.domain.fake.UserRepositoryFake
import com.joohnq.domain.use_case.AddUserUseCase
import com.joohnq.domain.use_case.GetUserUseCase
import com.joohnq.domain.use_case.UpdateSoughtHelpUseCase
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class UpdateSoughtHelpUseCaseTest {
    private val repository: UserRepositoryFake = UserRepositoryFake()
    private val getUseCase: GetUserUseCase = GetUserUseCase(repository)
    private val addUserCase: AddUserUseCase = AddUserUseCase(repository)
    private val useCase: UpdateSoughtHelpUseCase =
        UpdateSoughtHelpUseCase(repository)

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
                ProfessionalHelp.Yes
            ).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)

            //WHEN
            val user = getUseCase.invoke().getOrNull()

            //THEN
            assertThat(user).isNotNull()
            assertThat(user).isEqualTo(
                User(
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
                ProfessionalHelp.Yes
            ).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to update user sought help")
        }
}