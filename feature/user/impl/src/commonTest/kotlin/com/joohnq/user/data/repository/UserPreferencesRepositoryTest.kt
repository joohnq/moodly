package com.joohnq.user.data.repository

import com.joohnq.core.test.RobolectricTests
import com.joohnq.core.test.assertDoesNotThrow
import com.joohnq.core.test.createTestDriver
import com.joohnq.api.entity.UserPreferences
import com.joohnq.user.impl.data.database.UserDatabase
import com.joohnq.user.database.UserDatabaseSql
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class UserPreferencesRepositoryTest : RobolectricTests() {
    private lateinit var database: UserDatabaseSql
    private lateinit var repository: PreferencesRepository

    @BeforeTest
    fun setUp() {
        val driver = createTestDriver(UserDatabaseSql.Schema)
        database = UserDatabase(driver).invoke()
        repository = PreferencesRepositoryImpl(database)
    }

    private suspend fun initUserPreferences() {
        repository.insertUserPreferences()
    }

    @Test
    fun `testing get user preferences`() =
        runBlocking {
            //GIVEN
            assertDoesNotThrow { repository.insertUserPreferences() }

            //WHEN
            val item = assertDoesNotThrow { repository.getUserPreferences() }.getOrThrow()

            //THEN
            assertThat(item).isNotEqualTo(null)
            assertThat(item).isEqualTo(UserPreferences())
        }

    @Test
    fun `testing add user preferences`() =
        runBlocking {
            //WHEN
            assertDoesNotThrow {
                repository.addUserPreferences(
                    UserPreferences(
                        skipWelcome = true,
                        skipOnboarding = true,
                        skipAuth = true
                    )
                )
            }
            val item =
                assertDoesNotThrow { repository.getUserPreferences() }.getOrThrow()

            //THEN
            assertThat(item).isNotEqualTo(null)
            assertThat(item).isEqualTo(
                UserPreferences(
                    skipWelcome = true,
                    skipOnboarding = true,
                    skipAuth = true
                )
            )
        }

    @Test
    fun `testing update skip welcome`() =
        runBlocking {
            //GIVEN
            initUserPreferences()

            //WHEN
            assertDoesNotThrow {
                repository.updateSkipWelcome(true)
            }
            val item =
                assertDoesNotThrow { repository.getUserPreferences() }.getOrThrow()

            //THEN
            assertThat(item).isNotEqualTo(null)
            assertThat(item.skipWelcome).isEqualTo(true)
        }

    @Test
    fun `testing update skip onboarding`() =
        runBlocking {
            //GIVEN
            initUserPreferences()

            //WHEN
            assertDoesNotThrow {
                repository.updateSkipOnboarding(true)
            }
            val item =
                assertDoesNotThrow { repository.getUserPreferences() }.getOrThrow()

            //THEN
            assertThat(item).isNotEqualTo(null)
            assertThat(item.skipOnboarding).isEqualTo(true)
        }

    @Test
    fun `testing update skip auth`() =
        runBlocking {
            //GIVEN
            initUserPreferences()

            //WHEN
            assertDoesNotThrow {
                repository.updateSkipAuth(true)
            }
            val item =
                assertDoesNotThrow { repository.getUserPreferences() }.getOrThrow()

            //THEN
            assertThat(item).isNotEqualTo(null)
            assertThat(item.skipAuth).isEqualTo(true)
        }
}