package com.joohnq.moodapp.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier

class PaddingModifier {
    companion object {
        fun Modifier.paddingHorizontalLarge(): Modifier =
            padding(Dimens.Padding.HorizontalLarge)

        fun Modifier.paddingHorizontalMedium(): Modifier =
            padding(Dimens.Padding.HorizontalMedium)

        fun Modifier.paddingHorizontalSmall(): Modifier =
            this.padding(Dimens.Padding.HorizontalSmall)

        fun Modifier.paddingHorizontalExtraExtraSmall(): Modifier =
            this.padding(Dimens.Padding.HorizontalExtraExtraSmall)

        fun Modifier.paddingVerticalExtraLarge(): Modifier =
            padding(Dimens.Padding.VerticalExtraLarge)

        fun Modifier.paddingVerticalLarge(): Modifier =
            padding(Dimens.Padding.VerticalLarge)

        fun Modifier.paddingVerticalMedium(): Modifier =
            padding(Dimens.Padding.VerticalMedium)

        fun Modifier.paddingVerticalSmall(): Modifier =
            padding(Dimens.Padding.VerticalSmall)
    }
}