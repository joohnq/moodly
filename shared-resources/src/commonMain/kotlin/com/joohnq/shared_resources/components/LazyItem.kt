package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MentalHealthMetricItem(
    title: StringResource,
    icon: DrawableResource,
    backgroundColor: Color,
    onClick: () -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        onClick = onClick,
        colors = ComponentColors.Card.MentalHealthMetricColors(backgroundColor),
        shape = Dimens.Shape.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(title),
                    tint = Colors.White,
                    modifier = Modifier.size(Dimens.Icon)
                )
                HorizontalSpacer(10.dp)
                Text(
                    text = stringResource(title),
                    style = TextStyles.TextMdBold(),
                    color = Colors.White
                )
            }
            VerticalSpacer(24.dp)
            content(
                Modifier.heightIn(min = 130.dp, max = 140.dp)
                    .widthIn(min = 130.dp, max = 140.dp).fillMaxSize()
            )
        }
    }
}
