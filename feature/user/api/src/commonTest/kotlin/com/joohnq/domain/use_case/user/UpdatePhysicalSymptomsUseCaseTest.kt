package com.joohnq.domain.use_case.user

import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.User
import com.joohnq.domain.fake.UserRepositoryFake
import com.joohnq.domain.use_case.AddUserUseCase
import com.joohnq.domain.use_case.GetUserUseCase
import com.joohnq.domain.use_case.UpdatePhysicalSymptomsUseCase
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class UpdatePhysicalSymptomsUseCaseTest {
    private val repository: UserRepositoryFake = UserRepositoryFake()
    private val getUseCase: GetUserUseCase = GetUserUseCase(repository)
    private val addUserCase: AddUserUseCase = AddUserUseCase(repository)
    private val useCase: UpdatePhysicalSymptomsUseCase =
        UpdatePhysicalSymptomsUseCase(repository)

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
                PhysicalSymptoms.YesVeryPainful
            ).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)

            //WHEN
            val user = getUseCase.invoke().getOrNull()

            //THEN
            assertThat(user).isNotNull()
            assertThat(user).isEqualTo(
                User(
                    physicalSymptoms = PhysicalSymptoms.YesVeryPainful
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
                PhysicalSymptoms.YesVeryPainful
            ).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to update user physical symptoms")
        }
}