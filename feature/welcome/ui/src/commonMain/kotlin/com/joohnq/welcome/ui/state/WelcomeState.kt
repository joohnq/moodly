package com.joohnq.welcome.ui.state

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SnackbarHostState
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModelIntent

data class WelcomeState(
    val snackBarState: SnackbarHostState,
    val pagerState: PagerState,
    val onNext: () -> Unit,
    val onAction: (UserPreferenceViewModelIntent) -> Unit
)
