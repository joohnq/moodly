package com.joohnq.auth.impl.di

import com.joohnq.auth.impl.presentation.avatar.viewmodel.AvatarViewModel
import com.joohnq.auth.impl.presentation.user_name.viewmodel.UserNameViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authUiModule: Module = module {
    viewModelOf(::AvatarViewModel)
    viewModelOf(::UserNameViewModel)
}