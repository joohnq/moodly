package com.joohnq.domain.di

import com.joohnq.domain.use_case.user.AddUserUseCase
import com.joohnq.domain.use_case.user.GetUserUseCase
import com.joohnq.domain.use_case.user.InitUserUseCase
import com.joohnq.domain.use_case.user.UpdateMedicationsSupplementsUseCase
import com.joohnq.domain.use_case.user.UpdatePhysicalSymptomsUseCase
import com.joohnq.domain.use_case.user.UpdateSoughtHelpUseCase
import com.joohnq.domain.use_case.user.UpdateUserImageBitmapUseCase
import com.joohnq.domain.use_case.user.UpdateUserImageDrawableUseCase
import com.joohnq.domain.use_case.user.UpdateUserNameUseCase
import com.joohnq.domain.use_case.user.UpdateUserUseCase
import com.joohnq.domain.use_case.user_preferences.AddUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.GetUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.InsertUserPreferencesUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipAuthUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipOnboardingUseCase
import com.joohnq.domain.use_case.user_preferences.UpdateSkipWelcomeUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val userDomainModule = module {
    factoryOf(::AddUserUseCase)
    factoryOf(::GetUserUseCase)
    factoryOf(::InitUserUseCase)
    factoryOf(::UpdateMedicationsSupplementsUseCase)
    factoryOf(::UpdatePhysicalSymptomsUseCase)
    factoryOf(::UpdateSoughtHelpUseCase)
    factoryOf(::UpdateUserNameUseCase)
    factoryOf(::UpdateUserUseCase)
    factoryOf(::AddUserPreferencesUseCase)
    factoryOf(::GetUserPreferencesUseCase)
    factoryOf(::InsertUserPreferencesUseCase)
    factoryOf(::UpdateSkipAuthUseCase)
    factoryOf(::UpdateUserImageBitmapUseCase)
    factoryOf(::UpdateUserImageDrawableUseCase)
    factoryOf(::UpdateSkipOnboardingUseCase)
    factoryOf(::UpdateSkipWelcomeUseCase)
}