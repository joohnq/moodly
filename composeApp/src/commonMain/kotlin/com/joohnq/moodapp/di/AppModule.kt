package com.joohnq.moodapp.di

import com.joohnq.add.di.moodAddModule
import com.joohnq.api.di.userApiModule
import com.joohnq.auth.impl.di.authImplModule
import com.joohnq.datastore.di.dataStoreModule
import com.joohnq.freud_score.impl.di.freudScoreImplModule
import com.joohnq.history.di.moodHistoryModule
import com.joohnq.home.impl.di.homeImplModule
import com.joohnq.mood.add.di.moodImplModule
import com.joohnq.mood.api.di.moodApiModule
import com.joohnq.onboarding.impl.di.onboardingImplModule
import com.joohnq.overview.di.moodOverviewModule
import com.joohnq.preferences.api.di.preferencesApiModule
import com.joohnq.preferences.impl.di.preferencesImplModule
import com.joohnq.security.api.di.securityApiModule
import com.joohnq.security.impl.di.securityImplModule
import com.joohnq.self_journal.api.di.selfJournalApiModule
import com.joohnq.self_journal.di.selfJournalAddModule
import com.joohnq.self_journal.di.selfJournalEditModule
import com.joohnq.self_journal.di.selfJournalHistoryModule
import com.joohnq.self_journal.di.selfJournalOverviewModule
import com.joohnq.self_journal.impl.di.selfJournalImplModule
import com.joohnq.sleep_quality.add.di.sleepQualityAddModule
import com.joohnq.sleep_quality.api.di.sleepQualityApiModule
import com.joohnq.sleep_quality.history.di.sleepQualityHistoryModule
import com.joohnq.sleep_quality.impl.di.sleepQualityImplModule
import com.joohnq.sleep_quality.overview.di.sleepQualityOverviewModule
import com.joohnq.splash.impl.di.splashImplModule
import com.joohnq.storage.impl.di.coreStorageModule
import com.joohnq.stress_level.add.di.stressLevelAddModule
import com.joohnq.stress_level.api.di.stressLevelApiModule
import com.joohnq.stress_level.history.di.stressLevelHistoryModule
import com.joohnq.stress_level.impl.di.stressLevelImplModule
import com.joohnq.stress_level.overview.di.stressLevelOverviewModule
import com.joohnq.user.impl.di.userImplModule
import com.joohnq.welcome.impl.di.welcomeImplModule

val appModule =
    listOf(
        freudScoreImplModule,
        selfJournalImplModule,
        selfJournalApiModule,
        selfJournalAddModule,
        selfJournalEditModule,
        selfJournalHistoryModule,
        selfJournalOverviewModule,
        moodImplModule,
        moodApiModule,
        moodAddModule,
        moodHistoryModule,
        moodOverviewModule,
        sleepQualityImplModule,
        sleepQualityApiModule,
        sleepQualityAddModule,
        sleepQualityHistoryModule,
        sleepQualityOverviewModule,
        stressLevelImplModule,
        stressLevelApiModule,
        stressLevelAddModule,
        stressLevelHistoryModule,
        stressLevelOverviewModule,
        userApiModule,
        userImplModule,
        onboardingImplModule,
        authImplModule,
        coreStorageModule,
        securityImplModule,
        dataStoreModule,
        securityApiModule,
        homeImplModule,
        preferencesImplModule,
        preferencesApiModule,
        splashImplModule,
        welcomeImplModule,
    )