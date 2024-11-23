package com.joohnq.moodapp.ui.presentation.welcome.state

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent

data class WelcomeState(
    val snackBarState: SnackbarHostState,
    val pagerState: PagerState,
    val onNext: () -> Unit,
    val onAction: (UserPreferenceIntent) -> Unit
)