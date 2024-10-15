package com.joohnq.moodapp.di

import com.joohnq.moodapp.model.MoodDb
import com.joohnq.moodapp.model.User
import com.joohnq.moodapp.model.UserPreferences
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(Dispatchers::IO)
    singleOf(::UserPreferenceViewModel)
    singleOf(::MoodsViewModel)
    singleOf(::UserViewModel)
    single<RealmConfiguration> {
        RealmConfiguration.create(
            schema = setOf(
                User::class,
                MoodDb::class,
                UserPreferences::class
            )
        )
    }
    single<Realm> { Realm.open(get()) }
}