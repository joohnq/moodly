package com.joohnq.domain.use_case.user

import com.joohnq.core.test.assertThatContains
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.User
import com.joohnq.domain.fake.UserRepositoryFake
import com.joohnq.domain.use_case.AddUserUseCase
import com.joohnq.domain.use_case.GetUserUseCase
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class AddUserUseCaseTest {
    private val repository: UserRepositoryFake = UserRepositoryFake()
    private val getUseCase: GetUserUseCase = GetUserUseCase(repository)
    private val useCase: AddUserUseCase = AddUserUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling use case THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                User(
                    name = "name 3",
                    medicationsSupplements = MedicationsSupplements.PrescribedMedications
                )
            ).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)

            //WHEN
            val user = getUseCase.invoke().getOrNull()

            //THEN
            assertThat(user).isNotNull()
            user assertThatContains {
                it?.name == "name 3" && it.medicationsSupplements == MedicationsSupplements.PrescribedMedications
            }
        }

    @Test
    fun `GIVEN a invalid request WHEN calling use case THEN should return exception in failure`() =
        runBlocking {
            repository.updateShouldThrowError(true)
            //WHEN
            val res = useCase.invoke(
                User(
                    name = "name 3",
                    medicationsSupplements = MedicationsSupplements.PrescribedMedications
                )
            ).exceptionOrNull()

            //THEN
            assertThat(res?.message).isEqualTo("Failed to add user")
        }
}