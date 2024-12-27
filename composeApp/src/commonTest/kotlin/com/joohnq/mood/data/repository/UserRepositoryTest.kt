package com.joohnq.mood.data.repository

import com.joohnq.mood.data.dao.UserDAO
import com.joohnq.mood.domain.MedicationsSupplements
import com.joohnq.mood.domain.PhysicalSymptoms
import com.joohnq.mood.domain.User
import com.varabyte.truthish.assertThat
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class UserRepositoryTest {
    private lateinit var userDAO: UserDAO
    private lateinit var userRepository: UserRepository

    @BeforeTest
    fun setUp() {
        userDAO = mock(MockMode.autofill)
        userRepository = UserRepositoryImpl(userDAO)
    }

    @Test
    fun `test getUser with a valid res - should return the item`() = runTest {
        //GIVEN
        val user = User.init()
        everySuspend { userDAO.getUser() } returns user

        //WHEN
        val res = userRepository.getUser()

        //THEN
        assertThat(res).isEqualTo(user)
    }

    @Test
    fun `test addUser with a success execution - should return true`() = runTest {
        //GIVEN
        val item = User.init()
        everySuspend {
            userDAO.addUser(any<User>())
        } returns Unit

        //WHEN
        val res = userRepository.addUser(item)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            userDAO.addUser(any<User>())
        }
    }

    @Test
    fun `test addUser with a failed execution - should return false`() = runTest {
        //GIVEN
        val item = User.init()
        everySuspend {
            userDAO.addUser(any<User>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = userRepository.addUser(item)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            userDAO.addUser(any<User>())
        }
    }

    @Test
    fun `test updateUser with a success execution - should return true`() = runTest {
        //GIVEN
        val item = User.init()
        everySuspend {
            userDAO.updateUser(any<User>())
        } returns Unit

        //WHEN
        val res = userRepository.updateUser(item)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            userDAO.updateUser(any<User>())
        }
    }

    @Test
    fun `test updateUser with a failed execution - should return false`() = runTest {
        //GIVEN
        val item = User.init()
        everySuspend {
            userDAO.updateUser(any<User>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = userRepository.updateUser(item)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            userDAO.updateUser(any<User>())
        }
    }

    @Test
    fun `test initUser with a success execution - should return true`() = runTest {
        //GIVEN
        everySuspend {
            userDAO.initUser(any<User>())
        } returns Unit

        //WHEN
        val res = userRepository.initUser()

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            userDAO.initUser(any<User>())
        }
    }

    @Test
    fun `test initUser with a failed execution - should return false`() = runTest {
        //GIVEN
        everySuspend {
            userDAO.initUser(any<User>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = userRepository.initUser()

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            userDAO.initUser(any<User>())
        }
    }

    @Test
    fun `test updateUserName with a success execution - should return true`() =
        runTest {
            //GIVEN
            val name = "name"
            everySuspend {
                userDAO.updateUserName(any<String>())
            } returns Unit

            //WHEN
            val res = userRepository.updateUserName(name)

            //THEN
            assertThat(res).isTrue()

            verifySuspend {
                userDAO.updateUserName(any<String>())
            }
        }

    @Test
    fun `test updateUserName with a failed execution - should return false`() =
        runTest {
            //GIVEN
            val name = "name"
            everySuspend {
                userDAO.updateUserName(any<String>())
            } throws Exception("Something went wrong")

            //WHEN
            val res = userRepository.updateUserName(name)

            //THEN
            assertThat(res).isFalse()

            verifySuspend {
                userDAO.updateUserName(any<String>())
            }
        }

    @Test
    fun `test updateSoughtHelp with a success execution - should return true`() =
        runTest {
            //GIVEN
            everySuspend {
                userDAO.updateSoughtHelp(any<Boolean>())
            } returns Unit

            //WHEN
            val res = userRepository.updateSoughtHelp(true)

            //THEN
            assertThat(res).isTrue()

            verifySuspend {
                userDAO.updateSoughtHelp(any<Boolean>())
            }
        }

    @Test
    fun `test updateSoughtHelp with a failed execution - should return false`() =
        runTest {
            //GIVEN
            everySuspend {
                userDAO.updateSoughtHelp(any<Boolean>())
            } throws Exception("Something went wrong")

            //WHEN
            val res = userRepository.updateSoughtHelp(true)

            //THEN
            assertThat(res).isFalse()

            verifySuspend {
                userDAO.updateSoughtHelp(any<Boolean>())
            }
        }

    @Test
    fun `test updatePhysicalSymptoms with a success execution - should return true`() =
        runTest {
            //GIVEN
            val item = PhysicalSymptoms.No
            everySuspend {
                userDAO.updatePhysicalSymptoms(any<PhysicalSymptoms>())
            } returns Unit

            //WHEN
            val res = userRepository.updatePhysicalSymptoms(item)

            //THEN
            assertThat(res).isTrue()

            verifySuspend {
                userDAO.updatePhysicalSymptoms(any<PhysicalSymptoms>())
            }
        }

    @Test
    fun `test updatePhysicalSymptoms with a failed execution - should return false`() =
        runTest {
            //GIVEN
            val item = PhysicalSymptoms.No
            everySuspend {
                userDAO.updatePhysicalSymptoms(any<PhysicalSymptoms>())
            } throws Exception("Something went wrong")

            //WHEN
            val res = userRepository.updatePhysicalSymptoms(item)

            //THEN
            assertThat(res).isFalse()

            verifySuspend {
                userDAO.updatePhysicalSymptoms(any<PhysicalSymptoms>())
            }
        }

    @Test
    fun `test updateMedicationsSupplements with a success execution - should return true`() =
        runTest {
            //GIVEN
            val item = MedicationsSupplements.PrescribedMedications
            everySuspend {
                userDAO.updateMedicationsSupplements(any<MedicationsSupplements>())
            } returns Unit

            //WHEN
            val res = userRepository.updateMedicationsSupplements(item)

            //THEN
            assertThat(res).isTrue()

            verifySuspend {
                userDAO.updateMedicationsSupplements(any<MedicationsSupplements>())
            }
        }

    @Test
    fun `test updateMedicationsSupplements with a failed execution - should return false`() =
        runTest {
            //GIVEN
            val item = MedicationsSupplements.PrescribedMedications
            everySuspend {
                userDAO.updateMedicationsSupplements(any<MedicationsSupplements>())
            } throws Exception("Something went wrong")

            //WHEN
            val res = userRepository.updateMedicationsSupplements(item)

            //THEN
            assertThat(res).isFalse()

            verifySuspend {
                userDAO.updateMedicationsSupplements(any<MedicationsSupplements>())
            }
        }
}