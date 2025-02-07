package com.joohnq.mood.ui.viewmodel

import com.joohnq.mood.domain.entity.MoodRecord

sealed interface MoodIntent {
    data object GetMoodRecords : MoodIntent
    data class AddMoodRecord(val record: MoodRecord) : MoodIntent
    data class DeleteMoodRecord(val id: Int) : MoodIntent
}