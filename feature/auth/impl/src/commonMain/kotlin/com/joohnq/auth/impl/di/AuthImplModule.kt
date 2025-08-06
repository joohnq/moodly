package com.joohnq.auth.impl.di

import com.joohnq.auth.impl.ui.presentation.auth.AuthNameViewModel
import com.joohnq.auth.impl.ui.presentation.avatar.AvatarViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authImplModule: Module =
    module {
        viewModel<AvatarViewModel> {
            AvatarViewModel(
                updateUserImageBitmapUseCase = get(),
                updateUserImageDrawableUseCase = get()
            )
        }
        viewModel<AuthNameViewModel> {
            AuthNameViewModel(
                updateUserNameUseCase = get(),
                updateSkipAuthUseCase = get()
            )
        }
    }
