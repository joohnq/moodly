package com.joohnq.user.ui.di

import com.joohnq.domain.use_case.user.GetUserUseCase
import com.joohnq.domain.use_case.user.InitUserUseCase
import com.joohnq.domain.use_case.user.UpdateUserNameUseCase
import com.joohnq.domain.use_case.user.UpdateUserUseCase
import com.joohnq.domain.use_case.user_preferences.AddUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.GetUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipGetUserNameScreenUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipOnboardingScreenUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipWelcomeScreenUseCase
import com.joohnq.user.ui.presentation.get_user_name.viewmodel.GetUserNameViewModel
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan
class UserUiModule {
    @KoinViewModel
    fun provideUserViewModel(
        initUserUseCase: InitUserUseCase,
        updateUserUseCase: UpdateUserUseCase,
        getUserUseCase: GetUserUseCase,
        updateUserNameUseCase: UpdateUserNameUseCase,
        dispatcher: CoroutineDispatcher
    ): UserViewModel = UserViewModel(
        initUserUseCase = initUserUseCase,
        updateUserUseCase = updateUserUseCase,
        getUserUseCase = getUserUseCase,
        updateUserNameUseCase = updateUserNameUseCase,
        dispatcher = dispatcher
    )

    @KoinViewModel
    fun provideUserPreferencesViewModel(
        getUserPreferencesUseCase: GetUserPreferencesUseCase,
        addUserPreferencesUseCase: AddUserPreferencesUseCase,
        updateSkipWelcomeScreenUseCase: UpdateSkipWelcomeScreenUseCase,
        updateSkipOnboardingScreenUseCase: UpdateSkipOnboardingScreenUseCase,
        updateSkipGetUserNameScreenUseCase: UpdateSkipGetUserNameScreenUseCase,
        dispatcher: CoroutineDispatcher
    ): UserPreferenceViewModel = UserPreferenceViewModel(
        getUserPreferencesUseCase = getUserPreferencesUseCase,
        addUserPreferencesUseCase = addUserPreferencesUseCase,
        updateSkipWelcomeScreenUseCase = updateSkipWelcomeScreenUseCase,
        updateSkipOnboardingScreenUseCase = updateSkipOnboardingScreenUseCase,
        updateSkipGetUserNameScreenUseCase = updateSkipGetUserNameScreenUseCase,
        dispatcher = dispatcher
    )

    @KoinViewModel
    fun provideGetUserNameViewModel(): GetUserNameViewModel = GetUserNameViewModel()
}
