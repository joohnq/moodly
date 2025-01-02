package com.joohnq.health_journal.ui.di

import com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel.AddJournalingViewModel
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModel
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingViewModel
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val healthJournalUiModule = module {
    viewModelOf(::HealthJournalViewModel)
    viewModelOf(::AddJournalingViewModel)
    viewModelOf(::AllJournalViewModel)
    viewModelOf(::EditJournalingViewModel)
}
