package com.joohnq.freud_score.impl.di

import com.joohnq.freud_score.impl.presentation.freud_score.FreudScoreViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val freudScoreUiModule: Module = module {
    single<FreudScoreViewModel> {
        FreudScoreViewModel()
    }
}