package com.joohnq.self_journal.impl.ui.di

import com.joohnq.self_journal.impl.ui.presentation.add.AddSelfJournalViewModel
import com.joohnq.self_journal.impl.ui.presentation.edit.EditSelfJournalViewModel
import com.joohnq.self_journal.impl.ui.presentation.history.SelfJournalHistoryViewModel
import com.joohnq.self_journal.impl.ui.presentation.overview.SelfJournalOverviewViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val selfJournalUiModule: Module =
    module {
        single<SelfJournalOverviewViewModel> {
            SelfJournalOverviewViewModel(
                getSelfJournalsUseCase = get(),
                deleteSelfJournalsUseCase = get()
            )
        }
        single<AddSelfJournalViewModel> {
            AddSelfJournalViewModel(
                addSelfJournalsUseCase = get()
            )
        }
        single<SelfJournalHistoryViewModel> {
            SelfJournalHistoryViewModel(
                getSelfJournalsUseCase = get(),
                deleteSelfJournalsUseCase = get()
            )
        }
        single<EditSelfJournalViewModel> {
            EditSelfJournalViewModel(
                getSelfJournalByIdUseCase = get(),
                deleteSelfJournalsUseCase = get(),
                updateSelfJournalsUseCase = get()
            )
        }
    }
