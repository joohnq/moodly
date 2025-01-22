package com.joohnq.moodapp

import org.koin.core.module.Module

fun appComponentModule(appComponent: IosApplicationComponent): Module {
    return appComponent.item
}