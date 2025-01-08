package com.joohnq.moodapp.di

import com.joohnq.auth.ui.di.authUiModule
import com.joohnq.core.ui.di.coreUiModule
import com.joohnq.di.coreDiModule
import com.joohnq.domain.di.userDomainModule
import com.joohnq.freud_score.ui.di.freudScoreUiModule
import com.joohnq.health_journal.data.di.healthJournalDataModule
import com.joohnq.health_journal.data.di.healthJournalDriverFactory
import com.joohnq.health_journal.domain.di.healthJournalDomainModule
import com.joohnq.health_journal.ui.di.healthJournalUiModule
import com.joohnq.mood.data.di.moodDataModule
import com.joohnq.mood.data.di.moodDriverFactoryModule
import com.joohnq.mood.domain.di.moodDomainModule
import com.joohnq.mood.ui.di.moodUiModule
import com.joohnq.onboarding.ui.di.onboardingUiModule
import com.joohnq.sleep_quality.data.di.sleepQualityDataModule
import com.joohnq.sleep_quality.data.di.sleepQualityDriverFactoryModule
import com.joohnq.sleep_quality.domain.di.sleepQualityDomainModule
import com.joohnq.sleep_quality.ui.di.sleepQualityUiModule
import com.joohnq.storage.data.di.coreStorageModule
import com.joohnq.stress_level.data.di.stressLevelDataModule
import com.joohnq.stress_level.data.di.stressLevelDriverFactoryModule
import com.joohnq.stress_level.domain.di.stressLevelDomainModule
import com.joohnq.stress_level.ui.di.stressLevelUiModule
import com.joohnq.user.data.di.userDataModule
import com.joohnq.user.data.di.userDriverFactoryModule
import com.joohnq.user.ui.di.userUiModule

val appModule = listOf(
    freudScoreUiModule,
    healthJournalDataModule,
    healthJournalDomainModule,
    healthJournalUiModule,
    moodDataModule,
    moodDomainModule,
    moodUiModule,
    sleepQualityDataModule,
    sleepQualityDomainModule,
    sleepQualityUiModule,
    stressLevelDataModule,
    stressLevelDomainModule,
    stressLevelUiModule,
    userDataModule,
    userDomainModule,
    userUiModule,
    coreDiModule,
    coreUiModule,
    onboardingUiModule,
    authUiModule,
    healthJournalDriverFactory,
    moodDriverFactoryModule,
    sleepQualityDriverFactoryModule,
    stressLevelDriverFactoryModule,
    userDriverFactoryModule,
    coreStorageModule
)