package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.model.dao.StatsRecordDAO
import com.joohnq.moodapp.entities.FreudScore
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

class DateTimeViewModel : ViewModel() {
    private val _today:
            MutableStateFlow<LocalDate> = MutableStateFlow(DatetimeHelper.getLocalDate())
    val today: MutableStateFlow<LocalDate> = _today

    fun getTodayLocalDate(){
        _today.value = DatetimeHelper.getLocalDate()
    }
}