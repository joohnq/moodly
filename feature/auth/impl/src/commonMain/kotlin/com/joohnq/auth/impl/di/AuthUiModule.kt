package com.joohnq.auth.impl.di

import com.joohnq.auth.impl.presentation.auth.AuthNameViewModel
import com.joohnq.auth.impl.presentation.avatar.AvatarViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authUiModule: Module = module {
    viewModel<AvatarViewModel> {
        AvatarViewModel()
    }
    viewModel<AuthNameViewModel> {
        AuthNameViewModel()
    }
}
