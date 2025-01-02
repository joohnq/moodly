package com.joohnq.health_journal.domain.di

import com.joohnq.health_journal.domain.use_case.AddHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.CalculateHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.domain.use_case.DeleteHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.OrganizeByDateHealthJournalUseCase
import com.joohnq.health_journal.domain.use_case.OrganizeFromCreationHealthJournalFreudScoreUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val healthJournalDomainModule = module {
    factoryOf(::AddHealthJournalsUseCase)
    factoryOf(::CalculateHealthJournalFreudScoreUseCase)
    factoryOf(::DeleteHealthJournalsUseCase)
    factoryOf(::GetHealthJournalsInYearUseCase)
    factoryOf(::GetHealthJournalsUseCase)
    factoryOf(::OrganizeByDateHealthJournalUseCase)
    factoryOf(::OrganizeFromCreationHealthJournalFreudScoreUseCase)
}