package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.helper.DatetimeManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDateTime

class DateTimeViewModel : ViewModel() {
    private val _today:
            MutableStateFlow<LocalDateTime> = MutableStateFlow(DatetimeManager.getCurrentDateTime())
    val today: MutableStateFlow<LocalDateTime> = _today

    fun getTodayLocalDate() {
        _today.value = DatetimeManager.getCurrentDateTime()
    }
}