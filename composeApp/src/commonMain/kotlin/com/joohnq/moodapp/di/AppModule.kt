package com.joohnq.moodapp.di

import com.joohnq.auth.impl.ui.di.authUiModule
import com.joohnq.datastore.di.dataStoreModule
import com.joohnq.api.di.userDomainModule
import com.joohnq.freud_score.impl.ui.di.freudScoreUiModule
import com.joohnq.home.impl.ui.di.homeUiModule
import com.joohnq.mood.impl.data.di.moodDataModule
import com.joohnq.mood.impl.data.di.moodDriverFactoryModule
import com.joohnq.mood.api.di.moodDomainModule
import com.joohnq.mood.impl.ui.di.moodUiModule
import com.joohnq.onboarding.impl.ui.di.onboardingUiModule
import com.joohnq.preferences.api.di.preferencesDomainModule
import com.joohnq.preferences.impl.data.di.preferencesDataModule
import com.joohnq.preferences.impl.ui.di.preferencesUiModule
import com.joohnq.security.impl.data.di.securityAuthenticationModule
import com.joohnq.security.impl.data.di.securityDataModule
import com.joohnq.security.api.di.securityDomainModule
import com.joohnq.security.impl.ui.di.securityUiModule
import com.joohnq.self_journal.impl.data.di.selfJournalDataModule
import com.joohnq.self_journal.impl.data.di.selfJournalDriverFactory
import com.joohnq.self_journal.api.di.selfJournalDomainModule
import com.joohnq.self_journal.impl.ui.di.selfJournalUiModule
import com.joohnq.sleep_quality.impl.data.di.sleepQualityDataModule
import com.joohnq.sleep_quality.impl.data.di.sleepQualityDriverFactoryModule
import com.joohnq.sleep_quality.api.di.sleepQualityDomainModule
import com.joohnq.sleep_quality.impl.ui.di.sleepQualityUiModule
import com.joohnq.storage.impl.di.coreStorageModule
import com.joohnq.stress_level.impl.data.di.stressLevelDataModule
import com.joohnq.stress_level.impl.data.di.stressLevelDriverFactoryModule
import com.joohnq.stress_level.api.di.stressLevelDomainModule
import com.joohnq.stress_level.impl.ui.di.stressLevelUiModule
import com.joohnq.user.impl.data.di.userDataModule
import com.joohnq.user.impl.data.di.userDriverFactoryModule
import com.joohnq.user.impl.ui.di.userUiModule

val appModule = listOf(
    freudScoreUiModule,
    selfJournalDataModule,
    selfJournalDomainModule,
    selfJournalUiModule,
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
    onboardingUiModule,
    authUiModule,
    selfJournalDriverFactory,
    moodDriverFactoryModule,
    sleepQualityDriverFactoryModule,
    stressLevelDriverFactoryModule,
    userDriverFactoryModule,
    coreStorageModule,
    securityAuthenticationModule,
    securityUiModule,
    dataStoreModule,
    securityDataModule,
    securityDomainModule,
    homeUiModule,
    preferencesDataModule,
    preferencesDomainModule,
    preferencesUiModule
)