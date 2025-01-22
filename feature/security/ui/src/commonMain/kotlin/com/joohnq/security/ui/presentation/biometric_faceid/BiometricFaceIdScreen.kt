package com.joohnq.security.ui.presentation.biometric_faceid

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.security.ui.presentation.biometric_faceid.event.BiometricFaceIdEvent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel

@Composable
fun BiometricFaceIdScreen(
    onNavigateToDashboard: () -> Unit,
) {
    val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()

    fun onEvent(event: BiometricFaceIdEvent) {
        when (event) {
            BiometricFaceIdEvent.OnContinue -> {
                userPreferencesViewModel.onAction(
                    UserPreferenceIntent.UpdateSkipAuth()
                )
                onNavigateToDashboard()
            }
        }
    }

    return BiometricFaceIdUI(
        onEvent = ::onEvent
    )
}