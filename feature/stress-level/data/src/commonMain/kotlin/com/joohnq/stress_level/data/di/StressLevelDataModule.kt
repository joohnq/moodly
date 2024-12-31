package com.joohnq.stress_level.data.di

import com.joohnq.stress_level.data.repository.StressLevelRecordDAO
import com.joohnq.stress_level.data.repository.StressLevelRepositoryImpl
import com.joohnq.stress_level.domain.repository.StressLevelRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class StressLevelDataModule {
    @Single(binds = [StressLevelRepository::class])
    fun provideStressLevelRepository(
        stressLevelRecordDAO: StressLevelRecordDAO,
    ): StressLevelRepository = StressLevelRepositoryImpl(
        stressLevelRecordDAO = stressLevelRecordDAO
    )
}