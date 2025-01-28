package com.joohnq.shared_resources.remember

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.core.ui.getNow
import kotlinx.datetime.LocalDateTime

@Composable fun rememberGetNow(): LocalDateTime =
    remember { getNow() }