package com.joohnq.welcome.impl.presentation

import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class WelcomeParameterProvider : PreviewParameterProvider<Int> {
    override val values =
        sequenceOf(
            0,
            1,
            2,
            3,
            4,
            5,
            6
        )
}
