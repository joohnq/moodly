package com.joohnq.shared_resources.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NotFoundHorizontal(
    modifier: Modifier = Modifier,
    title: StringResource,
    subtitle: StringResource,
    image: DrawableResource,
    onClick: () -> Unit,
) {
    Card(
        colors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Brown80
        ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(16.dp).weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(title),
                    style = TextStyles.ParagraphSm(),
                    color = Colors.Gray60,
                )
                VerticalSpacer(10.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(subtitle),
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown60
                    )
                    Icon(
                        painter = painterResource(Drawables.Icons.Add),
                        contentDescription = null,
                        tint = Colors.Brown60,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
        }
    }
}

@Composable
fun NotFoundVertical(
    modifier: Modifier = Modifier,
    title: StringResource,
    subtitle: StringResource,
    image: DrawableResource,
    onClick: () -> Unit,
) {
    Card(
        colors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Brown80
        ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
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
                style = TextStyles.ParagraphSm(),
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
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(subtitle),
                    style = TextStyles.TextMdSemiBold(),
                    color = Colors.Brown60
                )
                Icon(
                    painter = painterResource(Drawables.Icons.Arrow),
                    contentDescription = null,
                    tint = Colors.Brown60,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

