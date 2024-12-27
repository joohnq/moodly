package com.joohnq.mood.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.joohnq.mood.theme.Dimens.Padding.HorizontalExtraExtraSmall
import com.joohnq.mood.theme.Dimens.Padding.HorizontalLarge
import com.joohnq.mood.theme.Dimens.Padding.HorizontalMedium
import com.joohnq.mood.theme.Dimens.Padding.HorizontalSmall
import com.joohnq.mood.theme.Dimens.Padding.VerticalExtraLarge
import com.joohnq.mood.theme.Dimens.Padding.VerticalLarge
import com.joohnq.mood.theme.Dimens.Padding.VerticalMedium
import com.joohnq.mood.theme.Dimens.Padding.VerticalSmall

class PaddingModifier {
    companion object {
        fun Modifier.paddingHorizontalLarge(): Modifier =
            padding(HorizontalLarge)

        fun Modifier.paddingHorizontalMedium(): Modifier =
            padding(HorizontalMedium)

        fun Modifier.paddingHorizontalSmall(): Modifier =
            this.padding(HorizontalSmall)

        fun Modifier.paddingHorizontalExtraExtraSmall(): Modifier =
            this.padding(HorizontalExtraExtraSmall)

        fun Modifier.paddingVerticalExtraLarge(): Modifier =
            padding(VerticalExtraLarge)

        fun Modifier.paddingVerticalLarge(): Modifier =
            padding(VerticalLarge)

        fun Modifier.paddingVerticalMedium(): Modifier =
            padding(VerticalMedium)

        fun Modifier.paddingVerticalSmall(): Modifier =
            padding(VerticalSmall)
    }
}