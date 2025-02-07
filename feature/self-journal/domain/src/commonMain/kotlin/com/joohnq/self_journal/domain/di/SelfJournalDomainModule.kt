package com.joohnq.self_journal.domain.di

import com.joohnq.self_journal.domain.use_case.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val selfJournalDomainModule = module {
    factoryOf(::AddSelfJournalsUseCase)
    factoryOf(::CalculateSelfJournalFreudScoreUseCase)
    factoryOf(::DeleteSelfJournalsUseCase)
    factoryOf(::GetSelfJournalsInYearUseCase)
    factoryOf(::GetSelfJournalsUseCase)
    factoryOf(::OrganizeByDateSelfJournalUseCase)
    factoryOf(::OrganizeFromCreationSelfJournalFreudScoreUseCase)
    factoryOf(::UpdateSelfJournalsUseCase)
    factoryOf(::CalculateSelfJournalsAverageUseCase)
}