package com.joohnq.health_journal.ui.di

import com.joohnq.health_journal.domain.use_case.AddHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.DeleteHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.UpdateHealthJournalsUseCase
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel.AddJournalingViewModel
import com.joohnq.health_journal.ui.presentation.all_journals.viewmodel.AllJournalViewModel
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingViewModel
import com.joohnq.health_journal.ui.viewmodel.HealthJournalViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan
class HealthJournalUiModule {
    @KoinViewModel
    fun provideHealthJournalViewModel(
        getHealthJournalsUseCase: GetHealthJournalsUseCase,
        deleteHealthJournalsUseCase: DeleteHealthJournalsUseCase,
        updateHealthJournalsUseCase: UpdateHealthJournalsUseCase,
        addHealthJournalsUseCase: AddHealthJournalsUseCase,
        dispatcher: CoroutineDispatcher,
    ): HealthJournalViewModel = HealthJournalViewModel(
        getHealthJournalsUseCase = getHealthJournalsUseCase,
        deleteHealthJournalsUseCase = deleteHealthJournalsUseCase,
        updateHealthJournalsUseCase = updateHealthJournalsUseCase,
        addHealthJournalsUseCase = addHealthJournalsUseCase,
        dispatcher = dispatcher
    )

    @KoinViewModel
    fun provideAddJournalingViewModel(): AddJournalingViewModel = AddJournalingViewModel()

    @KoinViewModel
    fun provideAllJournalViewModel(): AllJournalViewModel = AllJournalViewModel()

    @KoinViewModel
    fun provideEditJournalingViewModel(): EditJournalingViewModel = EditJournalingViewModel()
}
