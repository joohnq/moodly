package com.joohnq.shared_resources.components.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.spacer.HorizontalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingVerticalSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource

@Composable
fun ErrorView(errorText: String) {
    Box(
        modifier =
            Modifier
                .border(
                    width = 1.dp,
                    color = Colors.Orange40,
                    shape = Dimens.Shape.Circle
                ).background(
                    color = Colors.Orange20,
                    shape = Dimens.Shape.Circle
                ).paddingVerticalSmall()
                .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(Drawables.Icons.Filled.Warning),
                contentDescription = null,
                tint = Colors.Orange40,
                modifier = Modifier.size(20.dp)
            )
            HorizontalSpacer(10.dp)
            Text(
                text = errorText,
                style = TextStyles.textXsExtraBold(),
                color = Colors.Orange40
            )
        }
    }
}
