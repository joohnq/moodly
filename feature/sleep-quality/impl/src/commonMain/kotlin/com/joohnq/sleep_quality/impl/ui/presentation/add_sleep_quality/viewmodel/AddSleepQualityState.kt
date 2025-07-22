package com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality.viewmodel

import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource

data class AddSleepQualityState(
    val record: SleepQualityRecordResource = SleepQualityRecordResource(),
    val showStartTimePickerDialog: Boolean = false,
    val showEndTimePickerDialog: Boolean = false,
)