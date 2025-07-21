package com.joohnq.self_journal.domain.di

import com.joohnq.self_journal.domain.use_case.AddSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.DeleteSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.GetSelfJournalsUseCase
import com.joohnq.self_journal.domain.use_case.UpdateSelfJournalsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val selfJournalDomainModule = module {
    factoryOf(::AddSelfJournalsUseCase)
    factoryOf(::DeleteSelfJournalsUseCase)
    factoryOf(::GetSelfJournalsUseCase)
    factoryOf(::UpdateSelfJournalsUseCase)
}