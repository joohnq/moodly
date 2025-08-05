package com.joohnq.security.impl.ui.presentation.unlock

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import com.joohnq.security.impl.ui.presentation.pin.PinContract
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun UnLockContentPreview() {
    UnLockContent(
        state =
            UnlockContract.State(
                showBottomSheet = true,
                code = listOf(1, 2, 3, 4),
                focusedIndex = 0
            ),
    )
}