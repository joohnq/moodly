package com.joohnq.domain.di

import com.joohnq.domain.repository.UserPreferencesRepository
import com.joohnq.domain.repository.UserRepository
import com.joohnq.domain.use_case.user.AddUserUseCase
import com.joohnq.domain.use_case.user.GetUserUseCase
import com.joohnq.domain.use_case.user.InitUserUseCase
import com.joohnq.domain.use_case.user.UpdateMedicationsSupplementsUseCase
import com.joohnq.domain.use_case.user.UpdatePhysicalSymptomsUseCase
import com.joohnq.domain.use_case.user.UpdateSoughtHelpUseCase
import com.joohnq.domain.use_case.user.UpdateUserNameUseCase
import com.joohnq.domain.use_case.user.UpdateUserUseCase
import com.joohnq.domain.use_case.user_preferences.AddUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.GetUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.InsertUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipGetUserNameScreenUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipOnboardingScreenUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipWelcomeScreenUseCase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class UserDomainModule {
    @Factory
    fun provideAddUserUseCase(
        userRepository: UserRepository,
    ): AddUserUseCase = AddUserUseCase(
        userRepository = userRepository
    )

    @Factory
    fun provideGetUserUseCase(
        userRepository: UserRepository,
    ): GetUserUseCase = GetUserUseCase(
        userRepository = userRepository
    )

    @Factory
    fun provideInitUserUseCase(
        userRepository: UserRepository,
    ): InitUserUseCase = InitUserUseCase(
        userRepository = userRepository
    )

    @Factory
    fun provideUpdateMedicationsSupplementsUseCase(
        userRepository: UserRepository,
    ): UpdateMedicationsSupplementsUseCase = UpdateMedicationsSupplementsUseCase(
        userRepository = userRepository
    )

    @Factory
    fun provideUpdatePhysicalSymptomsUseCase(
        userRepository: UserRepository,
    ): UpdatePhysicalSymptomsUseCase = UpdatePhysicalSymptomsUseCase(
        userRepository = userRepository
    )

    @Factory
    fun provideUpdateSoughtHelpUseCase(
        userRepository: UserRepository,
    ): UpdateSoughtHelpUseCase = UpdateSoughtHelpUseCase(
        userRepository = userRepository
    )

    @Factory
    fun provideUpdateUserNameUseCase(
        userRepository: UserRepository,
    ): UpdateUserNameUseCase = UpdateUserNameUseCase(
        userRepository = userRepository
    )

    @Factory
    fun provideUpdateUserUseCase(
        userRepository: UserRepository,
    ): UpdateUserUseCase = UpdateUserUseCase(
        userRepository = userRepository
    )

    @Factory
    fun provideAddUserPreferencesUseCase(
        userPreferencesRepository: UserPreferencesRepository,
    ): AddUserPreferencesUseCase = AddUserPreferencesUseCase(
        userPreferencesRepository = userPreferencesRepository
    )

    @Factory
    fun provideGetUserPreferencesUseCase(
        userPreferencesRepository: UserPreferencesRepository,
    ): GetUserPreferencesUseCase = GetUserPreferencesUseCase(
        userPreferencesRepository = userPreferencesRepository
    )

    @Factory
    fun provideInsertUserPreferencesUseCase(
        userPreferencesRepository: UserPreferencesRepository,
    ): InsertUserPreferencesUseCase = InsertUserPreferencesUseCase(
        userPreferencesRepository = userPreferencesRepository
    )

    @Factory
    fun provideUpdateSkipGetUserNameScreenUseCase(
        userPreferencesRepository: UserPreferencesRepository,
    ): UpdateSkipGetUserNameScreenUseCase = UpdateSkipGetUserNameScreenUseCase(
        userPreferencesRepository = userPreferencesRepository
    )

    @Factory
    fun provideUpdateSkipOnboardingScreenUseCase(
        userPreferencesRepository: UserPreferencesRepository,
    ): UpdateSkipOnboardingScreenUseCase = UpdateSkipOnboardingScreenUseCase(
        userPreferencesRepository = userPreferencesRepository
    )

    @Factory
    fun provideUpdateSkipWelcomeScreenUseCase(
        userPreferencesRepository: UserPreferencesRepository,
    ): UpdateSkipWelcomeScreenUseCase = UpdateSkipWelcomeScreenUseCase(
        userPreferencesRepository = userPreferencesRepository
    )
}