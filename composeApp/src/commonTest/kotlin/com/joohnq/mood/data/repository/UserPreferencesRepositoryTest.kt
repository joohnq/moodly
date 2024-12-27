package com.joohnq.mood.data.repository

import com.joohnq.mood.data.dao.UserPreferencesDAO
import com.joohnq.mood.domain.UserPreferences
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

class UserPreferencesRepositoryTest {
    private lateinit var userPreferencesDAO: UserPreferencesDAO
    private lateinit var userPreferencesRepository: UserPreferencesRepository

    @BeforeTest
    fun setUp() {
        userPreferencesDAO = mock(MockMode.autofill)
        userPreferencesRepository = UserPreferencesRepositoryImpl(userPreferencesDAO)
    }

    @Test
    fun `test getUserPreferences with a valid res - should return the item`() = runTest {
        //GIVEN
        val userPreferences = UserPreferences.init()
        everySuspend { userPreferencesDAO.getUserPreferences() } returns userPreferences

        //WHEN
        val res = userPreferencesRepository.getUserPreferences()

        //THEN
        assertThat(res).isEqualTo(userPreferences)
    }

    @Test
    fun `test addUserPreferences with a success execution - should return true`() = runTest {
        //GIVEN
        val item =
            UserPreferences.init()
        everySuspend {
            userPreferencesDAO.addUserPreferences(any<UserPreferences>())
        } returns Unit

        //WHEN
        val res = userPreferencesRepository.addUserPreferences(item)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            userPreferencesDAO.addUserPreferences(any<UserPreferences>())
        }
    }

    @Test
    fun `test addUserPreferences with a failed execution - should return false`() = runTest {
        //GIVEN
        val item = UserPreferences.init()
        everySuspend {
            userPreferencesDAO.addUserPreferences(any<UserPreferences>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = userPreferencesRepository.addUserPreferences(item)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            userPreferencesDAO.addUserPreferences(any<UserPreferences>())
        }
    }

    @Test
    fun `test insertUserPreferences with a success execution - should return true`() = runTest {
        //GIVEN
        everySuspend {
            userPreferencesDAO.addUserPreferences()
        } returns Unit

        //WHEN
        val res = userPreferencesRepository.insertUserPreferences()

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            userPreferencesDAO.addUserPreferences(any<UserPreferences>())
        }
    }

    @Test
    fun `test insertUserPreferences with a failed execution - should return false`() = runTest {
        //GIVEN
        everySuspend {
            userPreferencesDAO.addUserPreferences()
        } throws Exception("Something went wrong")

        //WHEN
        val res = userPreferencesRepository.insertUserPreferences()

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            userPreferencesDAO.addUserPreferences(any<UserPreferences>())
        }
    }

    @Test
    fun `test updateSkipWelcomeScreen with a success execution - should return true`() = runTest {
        //GIVEN
        everySuspend {
            userPreferencesDAO.updateSkipWelcomeScreen(any<Boolean>())
        } returns Unit

        //WHEN
        val res = userPreferencesRepository.updateSkipWelcomeScreen(true)

        //THEN
        assertThat(res).isTrue()

        verifySuspend {
            userPreferencesDAO.updateSkipWelcomeScreen(any<Boolean>())
        }
    }

    @Test
    fun `test updateSkipWelcomeScreen with a failed execution - should return false`() = runTest {
        //GIVEN
        everySuspend {
            userPreferencesDAO.updateSkipWelcomeScreen(any<Boolean>())
        } throws Exception("Something went wrong")

        //WHEN
        val res = userPreferencesRepository.updateSkipWelcomeScreen(true)

        //THEN
        assertThat(res).isFalse()

        verifySuspend {
            userPreferencesDAO.updateSkipWelcomeScreen(any<Boolean>())
        }
    }

    @Test
    fun `test updateSkipOnboardingScreen with a success execution - should return true`() =
        runTest {
            //GIVEN
            everySuspend {
                userPreferencesDAO.updateSkipOnboardingScreen(any<Boolean>())
            } returns Unit

            //WHEN
            val res = userPreferencesRepository.updateSkipOnboardingScreen(true)

            //THEN
            assertThat(res).isTrue()

            verifySuspend {
                userPreferencesDAO.updateSkipOnboardingScreen(any<Boolean>())
            }
        }

    @Test
    fun `test updateSkipOnboardingScreen with a failed execution - should return false`() =
        runTest {
            //GIVEN
            everySuspend {
                userPreferencesDAO.updateSkipOnboardingScreen(any<Boolean>())
            } throws Exception("Something went wrong")

            //WHEN
            val res = userPreferencesRepository.updateSkipOnboardingScreen(true)

            //THEN
            assertThat(res).isFalse()

            verifySuspend {
                userPreferencesDAO.updateSkipOnboardingScreen(any<Boolean>())
            }
        }

    @Test
    fun `test updateSkipGetUserNameScreen with a success execution - should return true`() =
        runTest {
            //GIVEN
            everySuspend {
                userPreferencesDAO.updateSkipGetUserNameScreen(any<Boolean>())
            } returns Unit

            //WHEN
            val res = userPreferencesRepository.updateSkipGetUserNameScreen(true)

            //THEN
            assertThat(res).isTrue()

            verifySuspend {
                userPreferencesDAO.updateSkipGetUserNameScreen(any<Boolean>())
            }
        }

    @Test
    fun `test updateSkipGetUserNameScreen with a failed execution - should return false`() =
        runTest {
            //GIVEN
            everySuspend {
                userPreferencesDAO.updateSkipGetUserNameScreen(any<Boolean>())
            } throws Exception("Something went wrong")

            //WHEN
            val res = userPreferencesRepository.updateSkipGetUserNameScreen(true)

            //THEN
            assertThat(res).isFalse()

            verifySuspend {
                userPreferencesDAO.updateSkipGetUserNameScreen(any<Boolean>())
            }
        }
}