package com.joohnq.self_journal.di

import com.joohnq.self_journal.presentation.SelfJournalHistoryViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val selfJournalHistoryModule: Module =
    module {
        viewModel {
            SelfJournalHistoryViewModel(
                getSelfJournalsUseCase = get(),
                deleteSelfJournalsUseCase = get()
            )
        }
    }