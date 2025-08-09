package com.joohnq.shared_resources.components.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotFoundHorizontalLayout(
    modifier: Modifier = Modifier,
    containerColor: Color,
    title: StringResource,
    subtitle: StringResource,
    image: DrawableResource,
    onClick: () -> Unit,
) {
    Card(
        colors =
            CardColors(
                containerColor = containerColor,
                contentColor = Colors.Brown80,
                disabledContainerColor = containerColor,
                disabledContentColor = Colors.Brown80
            ),
        shape = Dimens.Shape.Large,
        modifier = modifier.height(112.dp).fillMaxSize(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f).paddingAllSmall(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(title),
                    style = TextStyles.paragraphSm(),
                    color = Colors.Gray60
                )
                VerticalSpacer(10.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(subtitle),
                        style = TextStyles.textMdSemiBold(),
                        color = Colors.Brown60
                    )
                    Icon(
                        painter = painterResource(Drawables.Icons.Outlined.Add),
                        contentDescription = null,
                        tint = Colors.Brown60,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun NotFoundHorizontalLayout(
    modifier: Modifier = Modifier,
    containerColor: Color,
    title: StringResource,
    description: StringResource,
    text: StringResource,
    icon: DrawableResource,
    image: DrawableResource,
    onCreate: () -> Unit,
) {
    Card(
        colors =
            CardColors(
                containerColor = containerColor,
                contentColor = Color.Unspecified,
                disabledContainerColor = containerColor,
                disabledContentColor = Color.Unspecified
            ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
        onClick = onCreate
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.paddingAllSmall().weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(title),
                    style = TextStyles.textMdBold(),
                    color = Colors.Gray80
                )
                VerticalSpacer(10.dp)
                Text(
                    text = stringResource(description),
                    style = TextStyles.paragraphSm(),
                    color = Colors.Gray60
                )
                VerticalSpacer(10.dp)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(text),
                        style = TextStyles.textSmSemiBold(),
                        color = Colors.Brown60
                    )
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = Colors.Brown60,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}
