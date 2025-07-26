package com.joohnq.shared_resources.remember

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable fun rememberSnackBarState(): SnackbarHostState = remember { SnackbarHostState() }
