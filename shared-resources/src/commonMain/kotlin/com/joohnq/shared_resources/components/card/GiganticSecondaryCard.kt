package com.joohnq.shared_resources.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun GiganticSecondaryCard(
    modifier: Modifier = Modifier,
    containerColor: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    secondary: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Card(
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Colors.Brown80,
            disabledContainerColor = containerColor,
            disabledContentColor = Colors.Brown80
        ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().paddingAllSmall(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = title,
                        style = TextStyles.headingXsBold(),
                        color = Colors.Gray80
                    )
                    Text(
                        text = subtitle,
                        style = TextStyles.textSmRegular(),
                        color = Colors.Gray60
                    )
                }
                secondary()
            }
            VerticalSpacer(16.dp)
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Colors.Gray20)
            VerticalSpacer(16.dp)
            content()
        }
    }
}