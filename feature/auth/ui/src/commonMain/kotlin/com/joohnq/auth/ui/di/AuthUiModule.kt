package com.joohnq.auth.ui.di

import com.joohnq.auth.ui.presentation.avatar.viewmodel.AvatarViewModel
import com.joohnq.auth.ui.viewmodel.AuthViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authUiModule: Module = module {
    viewModelOf(::AvatarViewModel)
    singleOf(::AuthViewModel)
}