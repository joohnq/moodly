package com.joohnq.mood.domain.di

import com.joohnq.domain.IDatetimeProvider
import com.joohnq.mood.domain.repository.StatsRepository
import com.joohnq.mood.domain.use_case.AddStatsUseCase
import com.joohnq.mood.domain.use_case.CalculateStatsFreudScore
import com.joohnq.mood.domain.use_case.DeleteStatsUseCase
import com.joohnq.mood.domain.use_case.GetNextStatUseCase
import com.joohnq.mood.domain.use_case.GetPreviousStatUseCase
import com.joohnq.mood.domain.use_case.GetStatGroupByDateUseCase
import com.joohnq.mood.domain.use_case.GetStatsUseCase
import com.joohnq.mood.domain.use_case.OrganizeStatRangeUseCase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan
class MoodDomainModule {
    @Factory
    fun provideAddStatsUseCase(
        statsRepository: StatsRepository,
    ): AddStatsUseCase = AddStatsUseCase(
        statsRepository = statsRepository
    )

    @Factory
    fun provideCalculateStatsFreudScore(): CalculateStatsFreudScore = CalculateStatsFreudScore()

    @Factory
    fun provideDeleteStatsUseCase(
        statsRepository: StatsRepository,
    ): DeleteStatsUseCase = DeleteStatsUseCase(
        statsRepository = statsRepository
    )

    @Factory
    fun provideGetNextStatUseCase(): GetNextStatUseCase = GetNextStatUseCase()

    @Factory
    fun provideGetPreviousStatUseCase(): GetPreviousStatUseCase = GetPreviousStatUseCase()

    @Factory
    fun provideGetStatGroupByDateUseCase(
        dateTimeProvider: IDatetimeProvider,
    ): GetStatGroupByDateUseCase = GetStatGroupByDateUseCase(
        dateTimeProvider = dateTimeProvider
    )

    @Factory
    fun provideGetStatsUseCase(
        statsRepository: StatsRepository,
    ): GetStatsUseCase = GetStatsUseCase(
        statsRepository = statsRepository
    )

    @Factory
    fun provideOrganizeStatRangeUseCase(): OrganizeStatRangeUseCase = OrganizeStatRangeUseCase()
}