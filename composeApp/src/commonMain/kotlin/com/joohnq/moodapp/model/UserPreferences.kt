package com.joohnq.moodapp.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class UserPreferences : RealmObject {
    @PrimaryKey
    var id: String = "1"
    var skipWelcomeScreen: Boolean = false
    var skipOnboardingScreen: Boolean = false
}
