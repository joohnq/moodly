package com.joohnq.user.data.di

import android.content.Context
import com.joohnq.user.data.driver.UserDriverFactory
import com.joohnq.user.data.driver.UserPreferencesDriverFactory
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
actual class UserDriverFactoryModule {
    @Single
    fun provideUserDriverFactory(context: Context): UserDriverFactory =
        UserDriverFactory(context)

    @Single
    fun provideUserPreferencesDriverFactory(context: Context): UserPreferencesDriverFactory =
        UserPreferencesDriverFactory(context)
}