package com.joohnq.moodapp.di

import com.joohnq.di.MainModule
import com.joohnq.domain.di.UserDomainModule
import com.joohnq.health_journal.data.di.HealthJournalDataModule
import com.joohnq.health_journal.domain.di.HealthJournalDomainModule
import com.joohnq.health_journal.ui.di.HealthJournalUiModule
import com.joohnq.mood.data.di.MoodDataModule
import com.joohnq.mood.domain.di.MoodDomainModule
import com.joohnq.mood.ui.di.MoodUiModule
import com.joohnq.sleep_quality.data.di.SleepQualityDataModule
import com.joohnq.sleep_quality.domain.di.SleepQualityDomainModule
import com.joohnq.sleep_quality.ui.di.SleepQualityUiModule
import com.joohnq.stress_level.data.di.StressLevelDataModule
import com.joohnq.stress_level.domain.di.StressLevelDomainModule
import com.joohnq.stress_level.ui.di.StressLevelUiModule
import com.joohnq.user.data.di.UserDataModule
import com.joohnq.user.data.di.UserDriverFactoryModule
import com.joohnq.user.ui.di.UserUiModule
import org.koin.core.annotation.Module

@Module(
    includes = [HealthJournalDataModule::class,
        HealthJournalDomainModule::class,
        HealthJournalUiModule::class,
        MoodDataModule::class,
        MoodDomainModule::class,
        MoodUiModule::class,
        SleepQualityDataModule::class,
        SleepQualityDomainModule::class,
        SleepQualityUiModule::class,
        StressLevelDataModule::class,
        StressLevelDomainModule::class,
        StressLevelUiModule::class,
        UserDataModule::class,
        UserDomainModule::class,
        UserUiModule::class,
        UserDriverFactoryModule::class,
        MainModule::class
    ]
)
class AppModule