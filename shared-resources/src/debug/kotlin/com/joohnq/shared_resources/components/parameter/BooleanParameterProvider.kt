package com.joohnq.shared_resources.components.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class BooleanParameterProvider : PreviewParameterProvider<Boolean> {
    override val values =
        sequenceOf(
            true,
            false
        )
}
