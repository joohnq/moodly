package com.joohnq.health_journal.domain.di

import com.joohnq.health_journal.domain.use_case.AddHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.CalculateHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.domain.use_case.DeleteHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsByDate
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.OrganizeByDateHealthJournalUseCase
import com.joohnq.health_journal.domain.use_case.OrganizeFromCreationHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.domain.use_case.UpdateHealthJournalsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val healthJournalDomainModule = module {
    factoryOf(::AddHealthJournalsUseCase)
    factoryOf(::CalculateHealthJournalFreudScoreUseCase)
    factoryOf(::DeleteHealthJournalsUseCase)
    factoryOf(::GetHealthJournalsByDate)
    factoryOf(::GetHealthJournalsInYearUseCase)
    factoryOf(::GetHealthJournalsUseCase)
    factoryOf(::OrganizeByDateHealthJournalUseCase)
    factoryOf(::OrganizeFromCreationHealthJournalFreudScoreUseCase)
    factoryOf(::UpdateHealthJournalsUseCase)
}