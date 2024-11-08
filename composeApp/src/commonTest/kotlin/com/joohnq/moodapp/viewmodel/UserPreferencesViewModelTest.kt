//package com.joohnq.moodapp.viewmodel
//
//import com.joohnq.moodapp.model.dao.UserPreferencesDAO
//import com.joohnq.moodapp.entities.UserPreferences
//import com.joohnq.moodapp.view.state.UiState
//import com.varabyte.truthish.assertThat
//import dev.mokkery.answering.returns
//import dev.mokkery.answering.throws
//import dev.mokkery.everySuspend
//import dev.mokkery.mock
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.cancel
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.flow.toList
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.UnconfinedTestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import kotlin.test.AfterTest
//import kotlin.test.BeforeTest
//import kotlin.test.Test
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class UserPreferencesViewModelTest {
//    private lateinit var userPreferenceViewModel: UserPreferenceViewModel
//    private lateinit var userPreferencesDAO: UserPreferencesDAO
//    private val testDispatcher: CoroutineDispatcher = StandardTestDispatcher()
//
//    @BeforeTest
//    fun setUp() {
//        Dispatchers.setMain(testDispatcher)
//
//        userPreferencesDAO = mock()
//        userPreferenceViewModel = UserPreferenceViewModel(userPreferencesDAO, testDispatcher)
//    }
//
//
//    @AfterTest
//    fun tearDown() {
//        Dispatchers.resetMain()
//        testDispatcher.cancel()
//    }
//
//    /**
//     * Test GetUserPreferences
//     * test getUserPreferences with a success user preferences response the userPreferences value should be Idle, Loading and Success
//     */
//    @Test
//    fun `test getUserPreferences with a success user preferences response the userPreferences value should be Idle - Loading so Success`() =
//        runTest {
//            // Given
//            val mock = UserPreferences()
//            userPreferencesDAO = mock<UserPreferencesDAO> {
//                everySuspend { getUserPreferences() } returns flowOf(mock)
//            }
//
//            userPreferenceViewModel = UserPreferenceViewModel(
//                userPreferencesDAO,
//                testDispatcher
//            )
//
//            //When
//            val states = mutableListOf<UiState<UserPreferences>>()
//            val job = launch(UnconfinedTestDispatcher(testScheduler)) {
//                userPreferenceViewModel.userPreferencesState.toList(states)
//            }
//
//            userPreferenceViewModel.getUserPreferences()
//
//            testScheduler.advanceUntilIdle()
//
//            // Then
//            assertThat(states.size).isEqualTo(3)
//            assertThat(states[0]).isEqualTo(UiState.Idle)
//            assertThat(states[1]).isEqualTo(UiState.Loading)
//            assertThat(states[2]).isEqualTo(UiState.Success(mock))
//
//            job.cancel()
//        }
//
//    /**
//     * Test GetMoods
//     * test getUserPreferences with a success user preferences response the userPreferences value should be Idle, Loading and Failure
//     */
//    @Test
//    fun `test getUserPreferences with a success user preferences response the userPreferences value should be Idle - Loading so Failure`() =
//        runTest {
//            // Given
//            val exception = "Something went wrong"
//            userPreferencesDAO = mock<UserPreferencesDAO> {
//                everySuspend { getUserPreferences() } returns flow { throw Exception(exception) }
//            }
//
//            userPreferenceViewModel = UserPreferenceViewModel(
//                userPreferencesDAO,
//                testDispatcher
//            )
//
//            // When
//            val states = mutableListOf<UiState<UserPreferences>>()
//            val job = launch(UnconfinedTestDispatcher(testScheduler)) {
//                userPreferenceViewModel.userPreferencesState.toList(states)
//            }
//
//            userPreferenceViewModel.getUserPreferences()
//
//            testScheduler.advanceUntilIdle()
//
//            // Then
//            assertThat(states.size).isEqualTo(3)
//            assertThat(states[0]).isEqualTo(UiState.Idle)
//            assertThat(states[1]).isEqualTo(UiState.Loading)
//            assertThat(states[2]).isEqualTo(UiState.Error(exception))
//            assertThat((states[2] as UiState.Error).message).isEqualTo(exception)
//
//            job.cancel()
//        }
//
//    /**
//     * Test InitUserPreferences
//     * test initUserPreferences with a success execution, should return true
//     */
//    @Test
//    fun testInitUserPreferencesSuccess() = runTest {
//        // Given
//        userPreferencesDAO = mock {
//            everySuspend { insertUserPreferences() } returns Unit
//        }
//        userPreferenceViewModel = UserPreferenceViewModel(userPreferencesDAO, testDispatcher)
//
//        // When
//        val res = userPreferenceViewModel.initUserPreferences()
//
//        // Then
//        assertThat(res).isTrue()
//    }
//
//    /**
//     * Test InitUserPreferences
//     * test initUserPreferences with a failure execution, should return false
//     */
//    @Test
//    fun testInitUserPreferencesFailure() = runTest {
//        // Given
//        userPreferencesDAO = mock {
//            everySuspend { insertUserPreferences() } throws Exception("Something went wrong")
//        }
//        userPreferenceViewModel = UserPreferenceViewModel(userPreferencesDAO, testDispatcher)
//
//        // When
//        val res = userPreferenceViewModel.initUserPreferences()
//
//        // Then
//        assertThat(res).isFalse()
//    }
//
//    /**
//     * Test SetSkipWelcomeScreen
//     * test setSkipWelcomeScreen with a success execution, should return true
//     */
//    @Test
//    fun testSetSkipWelcomeScreenSuccess() = runTest {
//        // Given
//        userPreferencesDAO = mock {
//            everySuspend { setSkipWelcomeScreen() } returns Unit
//        }
//        userPreferenceViewModel = UserPreferenceViewModel(userPreferencesDAO, testDispatcher)
//
//        // When
//        val res = userPreferenceViewModel.setSkipWelcomeScreen()
//
//        // Then
//        assertThat(res).isTrue()
//    }
//
//    /**
//     * Test SetSkipWelcomeScreen
//     * test setSkipWelcomeScreen with a failure execution, should return false
//     */
//    @Test
//    fun testSetSkipWelcomeScreenFailure() = runTest {
//        // Given
//        userPreferencesDAO = mock {
//            everySuspend { setSkipWelcomeScreen() } throws Exception("Something went wrong")
//        }
//        userPreferenceViewModel = UserPreferenceViewModel(userPreferencesDAO, testDispatcher)
//
//        // When
//        val res = userPreferenceViewModel.setSkipWelcomeScreen()
//
//        // Then
//        assertThat(res).isFalse()
//    }
//
//    /**
//     * Test SetSkipOnboardingScreen
//     * test setSkipOnboardingScreen with a success execution, should return true
//     */
//    @Test
//    fun testSetSkipOnboardingScreenSuccess() = runTest {
//        // Given
//        userPreferencesDAO = mock {
//            everySuspend { setSkipOnboardingScreen() } returns Unit
//        }
//        userPreferenceViewModel = UserPreferenceViewModel(userPreferencesDAO, testDispatcher)
//
//        // When
//        val res = userPreferenceViewModel.setSkipOnboardingScreen()
//
//        // Then
//        assertThat(res).isTrue()
//    }
//
//    /**
//     * Test SetSkipOnboardingScreen
//     * test setSkipOnboardingScreen with a failure execution, should return false
//     */
//    @Test
//    fun testSetSkipOnboardingScreenFailure() = runTest {
//        // Given
//        userPreferencesDAO = mock {
//            everySuspend { setSkipOnboardingScreen() } throws Exception("Something went wrong")
//        }
//        userPreferenceViewModel = UserPreferenceViewModel(userPreferencesDAO, testDispatcher)
//
//        // When
//        val res = userPreferenceViewModel.setSkipOnboardingScreen()
//
//        // Then
//        assertThat(res).isFalse()
//    }
//
//    /**
//     * Test SetSkipGetUserNameScreen
//     * test setSkipGetUserNameScreen with a success execution, should return true
//     */
//    @Test
//    fun testSetSkipGetUserNameScreenSuccess() = runTest {
//        // Given
//        userPreferencesDAO = mock {
//            everySuspend { setSkipGetUserNameScreen() } returns Unit
//        }
//        userPreferenceViewModel = UserPreferenceViewModel(userPreferencesDAO, testDispatcher)
//
//        // When
//        val res = userPreferenceViewModel.setSkipGetUserNameScreen()
//
//        // Then
//        assertThat(res).isTrue()
//    }
//
//    /**
//     * Test SetSkipGetUserNameScreen
//     * test setSkipGetUserNameScreen with a failure execution, should return false
//     */
//    @Test
//    fun testSetSkipGetUserNameScreenFailure() = runTest {
//        // Given
//        userPreferencesDAO = mock {
//            everySuspend { setSkipGetUserNameScreen() } throws Exception("Something went wrong")
//        }
//        userPreferenceViewModel = UserPreferenceViewModel(userPreferencesDAO, testDispatcher)
//
//        // When
//        val res = userPreferenceViewModel.setSkipGetUserNameScreen()
//
//        // Then
//        assertThat(res).isFalse()
//    }
//}
