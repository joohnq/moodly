package com.joohnq.user.ui.di

import com.joohnq.user.ui.presentation.get_user_name.viewmodel.GetUserNameViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val userUiModule: Module = module {
    singleOf(::UserViewModel)
    singleOf(::UserPreferenceViewModel)
    singleOf(::GetUserNameViewModel)
}