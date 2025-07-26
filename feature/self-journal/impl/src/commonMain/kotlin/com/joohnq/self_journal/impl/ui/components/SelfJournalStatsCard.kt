package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SelfJournalStatsCard(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: String,
    color: Color,
    backgroundColor: Color,
    description: String,
) {
    Card(
        modifier = modifier,
        colors = ComponentColors.Card.mainCardColors(),
        shape = Dimens.Shape.Large
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().paddingAllSmall(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier =
                    Modifier
                        .size(48.dp)
                        .background(color = backgroundColor, shape = Dimens.Shape.Small),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = description,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyles.textMdExtraBold(),
                    color = Colors.Brown80
                )
                Text(
                    text = description,
                    style = TextStyles.textSmSemiBold(),
                    color = Colors.Brown100Alpha64
                )
            }
        }
    }
}
