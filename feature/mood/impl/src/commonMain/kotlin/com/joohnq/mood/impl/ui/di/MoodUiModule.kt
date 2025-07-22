package com.joohnq.mood.impl.ui.di

import com.joohnq.mood.impl.ui.presentation.add_mood.viewmodel.AddMoodViewModel
import com.joohnq.mood.impl.ui.viewmodel.MoodViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moodUiModule: Module = module {
    singleOf(::MoodViewModel)
    singleOf(::AddMoodViewModel)
}