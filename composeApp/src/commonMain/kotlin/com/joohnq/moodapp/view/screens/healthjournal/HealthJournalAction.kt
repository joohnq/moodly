package com.joohnq.moodapp.view.screens.healthjournal

sealed class HealthJournalAction {
    data object OnGoBack : HealthJournalAction()
    data object OnAdd : HealthJournalAction()
}