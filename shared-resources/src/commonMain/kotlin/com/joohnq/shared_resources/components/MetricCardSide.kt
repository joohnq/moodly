package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.today
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MetricCardSide(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: StringResource,
    text: StringResource,
    suffix: StringResource,
    description: StringResource,
    content: @Composable (Modifier) -> Unit,
    color: Color,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = Dimens.Shape.Large,
        colors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.Gray80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Gray80
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = stringResource(title),
                        modifier = Modifier.size(20.dp),
                        tint = color
                    )
                    Text(
                        text = stringResource(title),
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Gray80
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.today),
                        style = TextStyles.TextSmRegular(),
                        color = Colors.Gray60
                    )
                    Icon(
                        painter = painterResource(Drawables.Icons.ArrowChevron),
                        contentDescription = stringResource(title),
                        modifier = Modifier.size(20.dp).rotate(180f),
                        tint = Colors.Gray40
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
                            text = stringResource(text),
                            style = TextStyles.Text2xlBold(),
                            color = Colors.Gray80
                        )
                        Text(
                            text = stringResource(suffix),
                            style = TextStyles.TextMdMedium(),
                            color = Colors.Gray80
                        )
                    }
                    VerticalSpacer(10.dp)
                    Text(
                        text = stringResource(description),
                        style = TextStyles.TextSmRegular(),
                        color = Colors.Gray60
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
fun MetricCardRow(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: StringResource,
    description: StringResource,
    content: @Composable () -> Unit,
    color: Color,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = Dimens.Shape.Large,
        colors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.Gray80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Gray80
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    androidx.compose.material3.Icon(
                        painter = painterResource(icon),
                        contentDescription = stringResource(title),
                        modifier = Modifier.size(20.dp),
                        tint = color
                    )
                    Text(
                        text = stringResource(title),
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Gray80
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.today),
                        style = TextStyles.TextSmRegular(),
                        color = Colors.Gray60
                    )
                    Icon(
                        painter = painterResource(Drawables.Icons.ArrowChevron),
                        contentDescription = stringResource(title),
                        modifier = Modifier.size(20.dp).rotate(180f),
                        tint = Colors.Gray40
                    )
                }
            }
            VerticalSpacer(10.dp)
            content()
            VerticalSpacer(10.dp)
            Text(
                text = stringResource(description),
                style = TextStyles.TextSmRegular(),
                color = Colors.Gray60
            )
        }
    }
}
