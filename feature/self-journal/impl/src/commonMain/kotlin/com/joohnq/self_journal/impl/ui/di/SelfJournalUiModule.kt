package com.joohnq.self_journal.impl.ui.di

import com.joohnq.self_journal.impl.ui.presentation.add_self_journal.AddSelfJournalViewModel
import com.joohnq.self_journal.impl.ui.presentation.edit_self_journal.EditSelfJournalViewModel
import com.joohnq.self_journal.impl.ui.presentation.self_journal_history.SelfJournalHistoryViewModel
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val selfJournalUiModule: Module = module {
    singleOf(::SelfJournalViewModel)
    viewModelOf(::AddSelfJournalViewModel)
    viewModelOf(::SelfJournalHistoryViewModel)
    viewModelOf(::EditSelfJournalViewModel)
}