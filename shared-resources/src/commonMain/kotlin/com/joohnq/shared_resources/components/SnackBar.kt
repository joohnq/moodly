package com.joohnq.shared_resources.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun SnackBarUI(
    paddingTop: Dp,
    data: SnackbarData?,
) {
    AnimatedVisibility(
        visible = data != null,
        modifier = Modifier.fillMaxWidth().padding(top = paddingTop)
    ) {
        Row(
            modifier = Modifier
                .paddingAllSmall()
                .shadow(
                    elevation = 1.dp,
                    shape = Dimens.Shape.ExtraSmall
                )
                .fillMaxWidth()
                .background(color = Colors.White, shape = Dimens.Shape.ExtraSmall)
                .paddingAllSmall(),
        ) {
            Text(
                text = data?.visuals?.message ?: "",
                color = Colors.Brown80,
                style = TextStyles.TextMdBold()
            )
        }
    }
}
