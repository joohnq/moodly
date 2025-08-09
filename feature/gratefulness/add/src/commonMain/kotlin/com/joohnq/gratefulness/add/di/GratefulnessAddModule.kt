package com.joohnq.gratefulness.add.di

import com.joohnq.gratefulness.add.presentation.GratefulnessAddViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gratefulnessAddModule: Module =
    module {
        viewModel {
            GratefulnessAddViewModel(
                addGratefulnessUseCase = get()
            )
        }
    }
