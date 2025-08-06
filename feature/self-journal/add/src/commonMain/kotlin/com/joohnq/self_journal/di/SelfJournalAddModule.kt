package com.joohnq.self_journal.di

import com.joohnq.self_journal.presentation.AddSelfJournalViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val selfJournalAddModule: Module =
    module {
        viewModel {
            AddSelfJournalViewModel(
                addSelfJournalsUseCase = get()
            )
        }
    }
