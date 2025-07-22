package com.joohnq.api.use_case.user_preferences

import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.entity.User
import com.joohnq.api.entity.UserPreferences
import com.joohnq.api.fake.UserPreferencesRepositoryFake
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class AddUserPreferencesUseCaseTest {
    private val repository: UserPreferencesRepositoryFake = UserPreferencesRepositoryFake()
    private val getUseCase: GetUserPreferencesUseCase = GetUserPreferencesUseCase(repository)
    private val useCase: AddUserPreferencesUseCase = AddUserPreferencesUseCase(repository)

    @Test
    fun `GIVEN a valid request WHEN calling use case THEN should return true`() =
        runBlocking {
            //WHEN
            val res = useCase.invoke(
                UserPreferences()
            ).getOrNull()

            //THEN
            assertThat(res).isEqualTo(true)

            //WHEN
            val user = getUseCase.invoke().getOrNull()

            //THEN
            assertThat(user).isNotNull()
            assertThat(user).isEqualTo(
                User(
                    name = "name 3",
                    medicationsSupplements = MedicationsSupplements.PrescribedMedications
                )
            )
        }

//    @Test
//    fun `GIVEN a invalid request WHEN calling use case THEN should return exception in failure`() =
//        runBlocking {
//            repository.updateShouldThrowError(true)
//            //WHEN
//            val res = useCase.invoke(
//                User(
//                    name = "name 3",
//                    medicationsSupplements = MedicationsSupplements.PrescribedMedications
//                )
//            ).exceptionOrNull()
//
//            //THEN
//            assertThat(res?.message).isEqualTo("Failed to add user")
//        }
}