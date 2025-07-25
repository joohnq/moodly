package com.joohnq.auth.impl.di

import com.joohnq.auth.impl.presentation.avatar.AvatarViewModel
import com.joohnq.auth.impl.presentation.auth.AuthNameViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authUiModule: Module = module {
    viewModelOf(::AvatarViewModel)
    viewModelOf(::AuthNameViewModel)
}