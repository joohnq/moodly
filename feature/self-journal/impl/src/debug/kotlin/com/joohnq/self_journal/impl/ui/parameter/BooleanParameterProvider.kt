package com.joohnq.self_journal.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class BooleanParameterProvider :
    PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(
        true,
        false
    )
}