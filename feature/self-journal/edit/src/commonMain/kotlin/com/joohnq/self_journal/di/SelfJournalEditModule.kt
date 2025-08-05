package com.joohnq.self_journal.di

import com.joohnq.self_journal.presentation.EditSelfJournalViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val selfJournalEditModule: Module =
    module {
        viewModel {
            EditSelfJournalViewModel(
                getSelfJournalByIdUseCase = get(),
                deleteSelfJournalsUseCase = get(),
                updateSelfJournalsUseCase = get()
            )
        }
    }