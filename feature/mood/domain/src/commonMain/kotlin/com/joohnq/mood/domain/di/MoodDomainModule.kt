package com.joohnq.mood.domain.di

import com.joohnq.mood.domain.use_case.AddStatsUseCase
import com.joohnq.mood.domain.use_case.CalculateStatsFreudScoreUseCase
import com.joohnq.mood.domain.use_case.DeleteStatsUseCase
import com.joohnq.mood.domain.use_case.GetNextStatUseCase
import com.joohnq.mood.domain.use_case.GetPreviousStatUseCase
import com.joohnq.mood.domain.use_case.GetStatGroupByDateUseCase
import com.joohnq.mood.domain.use_case.GetStatsByDate
import com.joohnq.mood.domain.use_case.GetStatsUseCase
import com.joohnq.mood.domain.use_case.OrganizeStatRangeUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val moodDomainModule = module {
    factoryOf(::AddStatsUseCase)
    factoryOf(::CalculateStatsFreudScoreUseCase)
    factoryOf(::DeleteStatsUseCase)
    factoryOf(::GetStatsByDate)
    factoryOf(::GetNextStatUseCase)
    factoryOf(::GetPreviousStatUseCase)
    factoryOf(::GetStatGroupByDateUseCase)
    factoryOf(::GetStatsUseCase)
    factoryOf(::OrganizeStatRangeUseCase)
}