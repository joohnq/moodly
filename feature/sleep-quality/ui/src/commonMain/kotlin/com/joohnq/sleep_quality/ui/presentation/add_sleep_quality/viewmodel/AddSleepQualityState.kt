package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel

import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource

data class AddSleepQualityState(
    val record: SleepQualityRecordResource = SleepQualityRecordResource(),
    val showStartTimePickerDialog: Boolean = false,
    val showEndTimePickerDialog: Boolean = false,
)