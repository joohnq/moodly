package com.joohnq.shared_resources.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Dimens.Padding.AllSmall
import com.joohnq.shared_resources.theme.Dimens.Padding.HorizontalExtraExtraSmall
import com.joohnq.shared_resources.theme.Dimens.Padding.HorizontalMedium
import com.joohnq.shared_resources.theme.Dimens.Padding.VerticalExtraLarge
import com.joohnq.shared_resources.theme.Dimens.Padding.VerticalLarge
import com.joohnq.shared_resources.theme.Dimens.Padding.VerticalMedium
import com.joohnq.shared_resources.theme.Dimens.Padding.VerticalSmall

object PaddingModifier {
    fun Modifier.paddingAllSmall(): Modifier = padding(AllSmall)

    fun Modifier.paddingHorizontalMedium(): Modifier = padding(HorizontalMedium)

    fun Modifier.paddingHorizontalExtraExtraSmall(): Modifier = this.padding(HorizontalExtraExtraSmall)

    fun Modifier.paddingVerticalExtraLarge(): Modifier = padding(VerticalExtraLarge)

    fun Modifier.paddingVerticalLarge(): Modifier = padding(VerticalLarge)

    fun Modifier.paddingVerticalMedium(): Modifier = padding(VerticalMedium)

    fun Modifier.paddingVerticalSmall(): Modifier = padding(VerticalSmall)
}
