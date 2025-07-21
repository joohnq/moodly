package com.joohnq.mood.impl.ui.viewmodel

import com.joohnq.mood.domain.entity.MoodRecord

sealed interface MoodIntent {
    data object GetAll : MoodIntent
    data class Add(val record: MoodRecord) : MoodIntent
    data class Delete(val id: Int) : MoodIntent
}