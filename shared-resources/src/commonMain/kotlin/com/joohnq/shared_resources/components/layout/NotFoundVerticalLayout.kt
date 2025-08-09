package com.joohnq.shared_resources.components.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
fun NotFoundVerticalLayout(
    modifier: Modifier = Modifier,
    title: StringResource,
    containerColor: Color = Colors.Gray5,
    actionText: StringResource,
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
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.paddingAllSmall(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(title),
                style = TextStyles.paragraphSm(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Colors.Gray20
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        10.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(actionText),
                    style = TextStyles.textMdSemiBold(),
                    color = Colors.Brown60,
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.Arrow),
                    contentDescription = null,
                    tint = Colors.Brown60,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun NotFoundVerticalLayout(
    modifier: Modifier = Modifier,
    title: StringResource,
    containerColor: Color = Colors.Gray5,
    subtitle: StringResource,
    actionText: StringResource,
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
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.paddingAllSmall(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(title),
                style = TextStyles.textMdBold(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(subtitle),
                style = TextStyles.paragraphSm(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Colors.Gray20
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        10.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(actionText),
                    style = TextStyles.textMdSemiBold(),
                    color = Colors.Brown60,
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.Arrow),
                    contentDescription = null,
                    tint = Colors.Brown60,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
