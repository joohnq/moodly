package com.joohnq.self_journal.di

import com.joohnq.self_journal.presentation.SelfJournalOverviewViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val selfJournalOverviewModule: Module =
    module {
        viewModel {
            SelfJournalOverviewViewModel(
                getSelfJournalsUseCase = get(),
                deleteSelfJournalsUseCase = get()
            )
        }
    }