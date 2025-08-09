package com.joohnq.gratefulness.api.entity.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.api.getNow
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "gratefulness")
data class GratefulnessDTO(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val iAmGratefulFor: String,
    val iAmThankfulFor: String,
    val smallThingIAppreciate: String,
    val description: String,
    val createdAt: LocalDateTime = getNow(),
)
