package com.joohnq.gratefulness.overview.di

import com.joohnq.gratefulness.overview.presentation.GratefulnessOverviewViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val gratefulnessOverviewModule: Module =
    module {
        single {
            GratefulnessOverviewViewModel(
                getGratefulnessUseCase = get(),
                deleteGratefulnessUseCase = get()
            )
        }
    }
