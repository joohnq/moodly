package com.joohnq.self_journal.impl.ui.di

import com.joohnq.self_journal.impl.ui.presentation.add_self_journal.viewmodel.AddJournalingViewModel
import com.joohnq.self_journal.impl.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalViewModel
import com.joohnq.self_journal.impl.ui.presentation.self_journal_history.viewmodel.SelfJournalHistoryViewModel
import com.joohnq.self_journal.impl.ui.viewmodel.SelfJournalViewModel
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
