package com.joohnq.moodapp.view.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier

class PaddingModifier {
    companion object {
        fun Modifier.paddingHorizontalMedium(): Modifier =
            padding(Dimens.Padding.HorizontalMedium)

        fun Modifier.paddingHorizontalSmall(): Modifier =
            this.padding(Dimens.Padding.HorizontalSmall)

        fun Modifier.paddingVerticalLarge(): Modifier =
            padding(Dimens.Padding.VerticalLarge)

        fun Modifier.paddingVerticalMedium(): Modifier =
            padding(Dimens.Padding.VerticalMedium)

        fun Modifier.paddingVerticalSmall(): Modifier =
            padding(Dimens.Padding.VerticalSmall)
    }
}