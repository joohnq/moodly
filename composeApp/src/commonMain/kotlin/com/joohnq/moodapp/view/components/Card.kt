package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingVerticalExtraLarge
import com.joohnq.moodapp.view.ui.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MindfulTrackerCardRow(
    icon: DrawableResource,
    color: Color,
    backgroundColor: Color,
    title: StringResource,
    subtitle: StringResource,
    content: @Composable (modifier: Modifier) -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().paddingHorizontalSmall(),
        onClick = onClick,
        shape = Dimens.Shape.Large,
        colors = ComponentColors.Card.MainCardColors(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.background(
                        color = backgroundColor,
                        shape = Dimens.Shape.Large
                    ).size(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = stringResource(title),
                        modifier = Modifier.size(Dimens.Icon),
                        tint = color
                    )
                }
                HorizontalSpacer(20.dp)
                Column(modifier = Modifier) {
                    Text(
                        text = stringResource(title),
                        style = TextStyles.TextLgExtraBold(),
                        color = Colors.Brown80
                    )
                    VerticalSpacer(5.dp)
                    Text(
                        text = stringResource(subtitle),
                        style = TextStyles.TextSmSemiBold(),
                        color = Colors.Brown100Alpha64
                    )
                }
            }
            Box(modifier = Modifier) {
                content(Modifier)
            }
        }
    }
}

@Composable
fun MindfulTrackerCardColumn(
    icon: DrawableResource,
    color: Color,
    backgroundColor: Color,
    title: StringResource,
    subtitle: StringResource? = null,
    content: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().paddingHorizontalSmall(),
        onClick = onClick,
        shape = Dimens.Shape.Large,
        colors = ComponentColors.Card.MainCardColors(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.background(
                    color = backgroundColor,
                    shape = Dimens.Shape.Large
                ).size(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(title),
                    modifier = Modifier.size(Dimens.Icon),
                    tint = color
                )
            }
            HorizontalSpacer(20.dp)
            Column {
                Text(
                    text = stringResource(title),
                    style = TextStyles.TextLgExtraBold(),
                    color = Colors.Brown80
                )
                VerticalSpacer(8.dp)
                Box(modifier = Modifier.fillMaxWidth()) {
                    content()
                }
                subtitle?.let {
                    VerticalSpacer(8.dp)
                    Text(
                        text = stringResource(subtitle),
                        style = TextStyles.TextSmSemiBold(),
                        color = Colors.Brown100Alpha64
                    )
                }
            }
        }
    }
}

@Composable
fun StressLevelCard(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: StringResource,
    value: String,
    content: @Composable () -> Unit
) {
    Card(
        colors = ComponentColors.Card.StressLevelCardColors(),
        shape = Dimens.Shape.ExtraLarge,
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxWidth().paddingVerticalExtraLarge()) {
            Row(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(title),
                    tint = Colors.Brown80,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(title),
                    style = TextStyles.TextMdExtraBold(),
                    color = Colors.Brown80
                )
            }
            VerticalSpacer(12.dp)
            Text(
                text = value,
                style = TextStyles.TextLgSemiBold(),
                color = Colors.Brown100Alpha64,
                modifier = Modifier.paddingHorizontalMedium()
            )
            VerticalSpacer(20.dp)
            content()
        }
    }
}

