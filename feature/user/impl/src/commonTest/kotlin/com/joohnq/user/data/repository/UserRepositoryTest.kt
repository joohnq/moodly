package com.joohnq.user.data.repository

import com.joohnq.core.test.RobolectricTests
import com.joohnq.core.test.assertDoesNotThrow
import com.joohnq.core.test.assertThatContains
import com.joohnq.core.test.createTestDriver
import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.MedicationsSupplements
import com.joohnq.api.entity.PhysicalSymptoms
import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.entity.User
import com.joohnq.api.repository.UserRepository
import com.joohnq.user.impl.data.database.UserDatabase
import com.joohnq.user.database.UserDatabaseSql
import com.joohnq.user.impl.data.repository.UserRepositoryImpl
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class UserRepositoryTest : RobolectricTests() {
    private lateinit var database: UserDatabaseSql
    private lateinit var repository: UserRepository

    @BeforeTest
    fun setUp() {
        val driver = createTestDriver(UserDatabaseSql.Schema)
        database = UserDatabase(driver).invoke()
        repository = UserRepositoryImpl(database)
    }

    private suspend fun initUser() {
        repository.addUser(User())
    }

    private suspend fun initExecuteAndVerify(
        addUser: suspend () -> Unit = { initUser() },
        block: suspend () -> Unit,
        verification: (User) -> Boolean,
    ) {
        //GIVEN
        addUser()

        //WHEN
        assertDoesNotThrow {
            block()
        }
        val item =
            assertDoesNotThrow { repository.getUser() }.getOrThrow()

        //THEN
        assertThat(item).isNotEqualTo(null)

        item assertThatContains { verification(it) }
    }

    @Test
    fun `testing get user`() =
        runBlocking {
            //GIVEN
            assertDoesNotThrow { repository.addUser(User()) }

            //WHEN
            val item = assertDoesNotThrow { repository.getUser() }.getOrThrow()

            //THEN
            assertThat(item).isNotEqualTo(null)
            item assertThatContains {
                it.id == 1 && it.name == "" && it.image == null
            }
        }

    @Test
    fun `testing add user`() =
        runBlocking {
            initExecuteAndVerify(
                addUser = {
                    assertDoesNotThrow {
                        repository.addUser(
                            User(
                                name = "John",
                                image = "image",
                            )
                        )
                    }
                },
                block = {
                    repository.updateUser(User(name = "John 2"))
                },
                verification = {
                    it.name == "John 2"
                }
            )
        }

    @Test
    fun `testing update user`() =
        runBlocking {
            initExecuteAndVerify(
                block = {
                    repository.updateUser(User(name = "John 2"))
                },
                verification = {
                    it.name == "John 2"
                }
            )
        }

    @Test
    fun `testing update user name`() =
        runBlocking {
            initExecuteAndVerify(
                block = {
                    repository.updateUserName("John 3")
                },
                verification = {
                    it.name == "John 3"
                }
            )
        }

    @Test
    fun `testing update user sought help`() =
        runBlocking {
            initExecuteAndVerify(
                block = {
                    repository.updateSoughtHelp(ProfessionalHelp.Yes)
                },
                verification = {
                    it.soughtHelp == ProfessionalHelp.Yes
                }
            )
        }

    @Test
    fun `testing update user physical symptoms`() =
        runBlocking {
            initExecuteAndVerify(
                block = {
                    repository.updatePhysicalSymptoms(PhysicalSymptoms.YesVeryPainful)
                },
                verification = {
                    it.physicalSymptoms == PhysicalSymptoms.YesVeryPainful
                }
            )
        }

    @Test
    fun `testing update user image`() =
        runBlocking {
            initExecuteAndVerify(
                block = {
                    repository.updateUserImage("image 3", ImageType.DEVICE)
                },
                verification = {
                    it.image == "image 3" && it.imageType == ImageType.DEVICE
                }
            )
        }

    @Test
    fun `testing update user medications supplements`() =
        runBlocking {
            initExecuteAndVerify(
                block = {
                    repository.updateMedicationsSupplements(MedicationsSupplements.PrescribedMedications)
                },
                verification = {
                    it.medicationsSupplements == MedicationsSupplements.PrescribedMedications
                }
            )
        }
}