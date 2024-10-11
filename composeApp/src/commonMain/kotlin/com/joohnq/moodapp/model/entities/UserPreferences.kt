package com.joohnq.moodapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi

@Entity(tableName = "user_preferences")
data class UserPreferences(
    @PrimaryKey val id: String = "1",
    @ColumnInfo(name = "skip_welcome_screen") val skipWelcomeScreen: Boolean = false
)
