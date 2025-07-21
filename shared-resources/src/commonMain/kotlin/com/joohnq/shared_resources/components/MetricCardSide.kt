package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.today
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MetricCardSide(
    modifier: Modifier = Modifier,
    containerColor: Color,
    dark: Boolean = false,
    icon: DrawableResource,
    title: String,
    text: String,
    suffix: String,
    description: String,
    content: @Composable (Modifier) -> Unit,
    color: Color,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = Dimens.Shape.Large,
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Colors.Gray80,
            disabledContainerColor = containerColor,
            disabledContentColor = Colors.Gray80
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().paddingAllSmall()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = title,
                        modifier = Modifier.size(20.dp),
                        tint = color
                    )
                    Text(
                        text = title,
                        style = TextStyles.TextMdSemiBold(),
                        color = if (dark) Colors.White else Colors.Gray80
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.today),
                        style = TextStyles.TextSmRegular(),
                        color = if (dark) Colors.White else Colors.Gray60
                    )
                    Icon(
                        painter = painterResource(Drawables.Icons.Outlined.ArrowOpen),
                        contentDescription = title,
                        modifier = Modifier.size(20.dp).rotate(180f),
                        tint = if (dark) Colors.White else Colors.Gray40
                    )
                }
            }
            VerticalSpacer(10.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = text,
                            style = TextStyles.Text2xlBold(),
                            color = if (dark) Colors.White else Colors.Gray80
                        )
                        Text(
                            text = suffix,
                            style = TextStyles.TextMdMedium(),
                            color = if (dark) Colors.White else Colors.Gray80
                        )
                    }
                    VerticalSpacer(10.dp)
                    Text(
                        text = description,
                        style = TextStyles.TextSmRegular(),
                        color = if (dark) Colors.White else Colors.Gray60
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    content(Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun MetricCardSide(
    modifier: Modifier = Modifier,
    containerColor: Color,
    dark: Boolean = false,
    icon: ImageVector,
    title: String,
    text: String,
    suffix: String,
    description: String,
    content: @Composable (Modifier) -> Unit,
    color: Color,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = Dimens.Shape.Large,
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Colors.Gray80,
            disabledContainerColor = containerColor,
            disabledContentColor = Colors.Gray80
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().paddingAllSmall()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        modifier = Modifier.size(20.dp),
                        tint = color
                    )
                    Text(
                        text = title,
                        style = TextStyles.TextMdSemiBold(),
                        color = if (dark) Colors.White else Colors.Gray80
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.today),
                        style = TextStyles.TextSmRegular(),
                        color = if (dark) Colors.White else Colors.Gray60
                    )
                    Icon(
                        painter = painterResource(Drawables.Icons.Outlined.ArrowOpen),
                        contentDescription = title,
                        modifier = Modifier.size(20.dp).rotate(180f),
                        tint = if (dark) Colors.White else Colors.Gray40
                    )
                }
            }
            VerticalSpacer(10.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = text,
                            style = TextStyles.Text2xlBold(),
                            color = if (dark) Colors.White else Colors.Gray80
                        )
                        Text(
                            text = suffix,
                            style = TextStyles.TextMdMedium(),
                            color = if (dark) Colors.White else Colors.Gray80
                        )
                    }
                    VerticalSpacer(10.dp)
                    Text(
                        text = description,
                        style = TextStyles.TextSmRegular(),
                        color = if (dark) Colors.White else Colors.Gray60
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    content(Modifier.fillMaxWidth())
                }
            }
        }
    }
}
