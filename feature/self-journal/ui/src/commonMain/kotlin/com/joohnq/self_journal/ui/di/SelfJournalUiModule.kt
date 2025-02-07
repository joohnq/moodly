package com.joohnq.self_journal.ui.di

import com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel.AddJournalingViewModel
import com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalViewModel
import com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryViewModel
import com.joohnq.self_journal.ui.viewmodel.SelfJournalViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val selfJournalUiModule: Module = module {
    singleOf(::SelfJournalViewModel)
    viewModelOf(::AddJournalingViewModel)
    viewModelOf(::SelfJournalHistoryViewModel)
    viewModelOf(::EditSelfJournalViewModel)
}
