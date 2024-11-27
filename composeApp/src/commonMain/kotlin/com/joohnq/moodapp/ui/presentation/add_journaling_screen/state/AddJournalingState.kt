package com.joohnq.moodapp.ui.presentation.add_journaling_screen.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.event.AddJournalingEvent
import com.joohnq.moodapp.viewmodel.AddingJournalingIntent
import com.joohnq.moodapp.viewmodel.HealthJournalIntent

data class AddJournalingState(
    val snackBarState: SnackbarHostState,
    val selectedMood: Mood?,
    val title: String,
    val titleError: String?,
    val desc: String,
    val sliderValue: Float,
    val selectedStressStressors: List<Stressor>,
    val onAddingAction: (AddingJournalingIntent) -> Unit = {},
    val onAction: (HealthJournalIntent) -> Unit = {},
    val onEvent: (AddJournalingEvent) -> Unit = {},
)