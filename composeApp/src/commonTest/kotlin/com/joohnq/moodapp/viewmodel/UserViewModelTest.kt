package com.joohnq.moodapp.viewmodel

import com.joohnq.moodapp.model.dao.UserDAO
import com.joohnq.moodapp.entities.MedicationsSupplements
import com.joohnq.moodapp.entities.PhysicalSymptoms
import com.varabyte.truthish.assertThat
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {
    private lateinit var userViewModel: UserViewModel
    private lateinit var userDAO: UserDAO
    private val testDispatcher: CoroutineDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }


    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    /**
     * Test SetUserSoughtHelp
     * Test with a success execution, should return true
     */
    @Test
    fun testSetUserSoughtHelpSuccess() = runTest {
        userDAO = mock {
            everySuspend { setUserSoughtHelp(any()) } returns Unit
        }

        userViewModel = UserViewModel(userDAO)

        val res = userViewModel.setUserSoughtHelp(true)

        assertThat(res).isTrue()
        verifySuspend { userDAO.setUserSoughtHelp(any()) }
    }

    /**
     * Test SetUserSoughtHelp
     * Test with a success execution, should return false
     */
    @Test
    fun testSetUserSoughtHelpFailed() = runTest {
        userDAO = mock {
            everySuspend { setUserSoughtHelp(any()) } throws Exception("Something went wrong")
        }

        userViewModel = UserViewModel(userDAO)

        val res = userViewModel.setUserSoughtHelp(true)

        assertThat(res).isFalse()
        verifySuspend { userDAO.setUserSoughtHelp(any()) }
    }

    /**
     * Test SetUserPhysicalPain
     * Test with a success execution, should return true
     */
    @Test
    fun testSetUserPhysicalPainSuccess() = runTest {
        userDAO = mock {
            everySuspend { setUserPhysicalPain(any()) } returns Unit
        }

        userViewModel = UserViewModel(userDAO)

        val res = userViewModel.setUserPhysicalPain(PhysicalSymptoms.No)

        assertThat(res).isTrue()
        verifySuspend { userDAO.setUserPhysicalPain(any()) }
    }

    /**
     * Test SetUserPhysicalPain
     * Test with a success execution, should return false
     */
    @Test
    fun testSetUserPhysicalPainFailed() = runTest {
        userDAO = mock {
            everySuspend { setUserPhysicalPain(any()) } throws Exception("Something went wrong")
        }

        userViewModel = UserViewModel(userDAO)

        val res = userViewModel.setUserPhysicalPain(PhysicalSymptoms.No)

        assertThat(res).isFalse()
        verifySuspend { userDAO.setUserPhysicalPain(any()) }
    }

    /**
     * Test SetUserMedicationsSupplements
     * Test with a success execution, should return true
     */
    @Test
    fun testSetUserMedicationsSupplementsSuccess() = runTest {
        userDAO = mock {
            everySuspend { setUserMedicationsSupplements(any()) } returns Unit
        }

        userViewModel = UserViewModel(userDAO)

        val res = userViewModel.setUserMedicationsSupplements(MedicationsSupplements.PrescribedMedications)

        assertThat(res).isTrue()
        verifySuspend { userDAO.setUserMedicationsSupplements(any()) }
    }

    /**
     * Test SetUserMedicationsSupplements
     * Test with a success execution, should return false
     */
    @Test
    fun testSetUserMedicationsSupplementsFailed() = runTest {
        userDAO = mock {
            everySuspend { setUserMedicationsSupplements(any()) } throws Exception("Something went wrong")
        }

        userViewModel = UserViewModel(userDAO)

        val res = userViewModel.setUserMedicationsSupplements(MedicationsSupplements.PrescribedMedications)

        assertThat(res).isFalse()
        verifySuspend { userDAO.setUserMedicationsSupplements(any()) }
    }

    /**
     * Test SetUserName
     * Test with a success execution, should return true
     */
    @Test
    fun testSetUserNameSuccess() = runTest {
        userDAO = mock {
            everySuspend { setUserName(any()) } returns Unit
        }

        userViewModel = UserViewModel(userDAO)

        val res = userViewModel.setUserName("NAME")

        assertThat(res).isTrue()
        verifySuspend { userDAO.setUserName(any()) }
    }

    /**
     * Test SetUserName
     * Test with a success execution, should return false
     */
    @Test
    fun testSetUserNameFailed() = runTest {
        userDAO = mock {
            everySuspend { setUserName(any()) } throws Exception("Something went wrong")
        }

        userViewModel = UserViewModel(userDAO)

        val res = userViewModel.setUserName("NAME")

        assertThat(res).isFalse()
        verifySuspend { userDAO.setUserName(any()) }
    }
}
