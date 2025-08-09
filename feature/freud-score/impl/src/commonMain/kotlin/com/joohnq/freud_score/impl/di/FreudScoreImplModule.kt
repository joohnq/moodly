package com.joohnq.freud_score.impl.di

import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val freudScoreImplModule: Module =
    module {
        single<FreudScoreViewModel> {
            FreudScoreViewModel(
                getMoodsUseCase = get()
            )
        }
    }
