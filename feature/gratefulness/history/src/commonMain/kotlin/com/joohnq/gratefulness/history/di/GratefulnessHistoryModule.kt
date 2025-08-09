package com.joohnq.gratefulness.history.di

import com.joohnq.gratefulness.history.presentation.GratefulnessHistoryViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gratefulnessHistoryModule: Module =
    module {
        viewModel<GratefulnessHistoryViewModel> {
            GratefulnessHistoryViewModel(
                getGratefulnessUseCase = get(),
                deleteGratefulnessUseCase = get()
            )
        }
    }
