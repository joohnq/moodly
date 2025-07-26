package com.joohnq.freud_score.impl.ui.di

import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val freudScoreUiModule: Module =
    module {
        single<FreudScoreViewModel> {
            FreudScoreViewModel()
        }
    }
