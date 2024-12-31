package com.joohnq.health_journal.domain.di

import com.joohnq.domain.IDatetimeProvider
import com.joohnq.health_journal.domain.repository.HealthJournalRepository
import com.joohnq.health_journal.domain.use_case.AddHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.CalculateHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.domain.use_case.DeleteHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsInYearUseCase
import com.joohnq.health_journal.domain.use_case.GetHealthJournalsUseCase
import com.joohnq.health_journal.domain.use_case.OrganizeByDateHealthJournalUseCase
import com.joohnq.health_journal.domain.use_case.OrganizeFromCreationHealthJournalFreudScoreUseCase
import com.joohnq.health_journal.domain.use_case.UpdateHealthJournalsUseCase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class HealthJournalDomainModule {

    @Factory
    fun provideAddHealthJournalsUseCase(
        healthJournalRepository: HealthJournalRepository,
    ): AddHealthJournalsUseCase = AddHealthJournalsUseCase(
        healthJournalRepository = healthJournalRepository
    )

    @Factory
    fun provideCalculateHealthJournalFreudScoreUseCase(): CalculateHealthJournalFreudScoreUseCase =
        CalculateHealthJournalFreudScoreUseCase()

    @Factory
    fun provideDeleteHealthJournalsUseCase(
        healthJournalRepository: HealthJournalRepository,
    ): DeleteHealthJournalsUseCase = DeleteHealthJournalsUseCase(
        healthJournalRepository = healthJournalRepository
    )

    @Factory
    fun provideGetHealthJournalsInYearUseCase(
        dateTimeProvider: IDatetimeProvider,
    ): GetHealthJournalsInYearUseCase = GetHealthJournalsInYearUseCase(
        dateTimeProvider = dateTimeProvider
    )

    @Factory
    fun provideGetHealthJournalsUseCase(
        healthJournalRepository: HealthJournalRepository,
    ): GetHealthJournalsUseCase = GetHealthJournalsUseCase(
        healthJournalRepository = healthJournalRepository
    )

    @Factory
    fun provideOrganizeByDateHealthJournalUseCase(
        dateTimeProvider: IDatetimeProvider,
    ): OrganizeByDateHealthJournalUseCase = OrganizeByDateHealthJournalUseCase(
        dateTimeProvider = dateTimeProvider
    )

    @Factory
    fun provideOrganizeFromCreationHealthJournalFreudScoreUseCase(
        dateTimeProvider: IDatetimeProvider,
    ): OrganizeFromCreationHealthJournalFreudScoreUseCase =
        OrganizeFromCreationHealthJournalFreudScoreUseCase(
            dateTimeProvider = dateTimeProvider
        )

    @Factory
    fun provideUpdateHealthJournalsUseCase(
        healthJournalRepository: HealthJournalRepository,
    ): UpdateHealthJournalsUseCase = UpdateHealthJournalsUseCase(
        healthJournalRepository = healthJournalRepository
    )
}
