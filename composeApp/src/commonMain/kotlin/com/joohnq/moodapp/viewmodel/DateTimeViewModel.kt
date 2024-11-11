package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.helper.DatetimeHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDateTime

class DateTimeViewModel : ViewModel() {
    private val _today:
            MutableStateFlow<LocalDateTime> = MutableStateFlow(DatetimeHelper.getLocalDateTime())
    val today: MutableStateFlow<LocalDateTime> = _today

    fun getTodayLocalDate() {
        _today.value = DatetimeHelper.getLocalDateTime()
    }
}