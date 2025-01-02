package com.joohnq.user.ui.di

import com.joohnq.user.ui.presentation.get_user_name.viewmodel.GetUserNameViewModel
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.UserViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val userUiModule = module {
    viewModelOf(::UserViewModel)
    viewModelOf(::UserPreferenceViewModel)
    viewModelOf(::GetUserNameViewModel)
}
