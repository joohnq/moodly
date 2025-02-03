package com.joohnq.shared_resources.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.DIcon
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.theme.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ButtonOutlined(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: StringResource,
    colors: ButtonColors,
    shape: Shape,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier.height(56.dp),
        colors = colors,
        enabled = enabled,
        shape = shape,
        onClick = onClick,
        border = BorderStroke(2.dp, colors.contentColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(text),
                style = TextStyles.TextLgExtraBold(),
            )
        }
    }
}

@Composable
fun ButtonTextAndIcon(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: StringResource,
    icon: DIcon,
    colors: ButtonColors,
    shape: Shape,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.height(56.dp),
        colors = colors,
        enabled = enabled,
        shape = shape,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(text),
                style = TextStyles.TextLgExtraBold(),
            )
            HorizontalSpacer(12.dp)
            Icon(icon.copy(tint = if (enabled) colors.contentColor else colors.disabledContentColor))
        }
    }
}

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: StringResource = Res.string.continue_word,
    onClick: () -> Unit,
) {
    ButtonTextAndIcon(
        modifier = modifier.height(56.dp),
        text = text,
        enabled = enabled,
        colors = ComponentColors.Button.MainButtonColors(),
        shape = Dimens.Shape.Circle,
        icon = DIcon(
            icon = Drawables.Icons.Arrow,
            modifier = Modifier.size(Dimens.Icon),
            contentDescription = Res.string.continue_word
        ),
        onClick = onClick
    )
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: StringResource,
    onClick: () -> Unit,
) {
    ButtonOutlined(
        modifier = modifier.height(56.dp),
        text = text,
        enabled = enabled,
        colors = ComponentColors.Button.MainButtonColorsInverted(),
        shape = Dimens.Shape.Circle,
        onClick = onClick
    )
}

@Composable
fun ButtonTextAndCheck(
    modifier: Modifier = Modifier,
    text: StringResource,
    onClick: () -> Unit,
) {
    ButtonTextAndIcon(
        modifier = modifier,
        text = text,
        colors = ComponentColors.Button.MainButtonColorsInverted(),
        shape = Dimens.Shape.Circle,
        icon = DIcon(
            icon = Drawables.Icons.Check,
            tint = Colors.Brown80,
            modifier = Modifier.size(Dimens.Icon),
            contentDescription = text
        ),
        onClick = onClick
    )
}

@Composable
fun BackButton(
    color: Color,
    backgroundColor: Color = Colors.Transparent,
    onClick: () -> Unit,
) {
    FilledIconButton(
        modifier = Modifier.size(48.dp),
        shape = Dimens.Shape.Circle,
        colors = IconButtonColors(
            containerColor = backgroundColor,
            contentColor = color,
            disabledContainerColor = backgroundColor,
            disabledContentColor = color
        ),
        onClick = onClick,
    ) {
        Icon(
            DIcon(
                icon = Drawables.Icons.ArrowOpen,
                tint = color,
                modifier = Modifier.size(Dimens.Icon),
                contentDescription = Res.string.go_back
            )
        )
    }
}

@Composable
fun IconContinueButton(
    modifier: Modifier = Modifier,
    colors: IconButtonColors,
    onClick: () -> Unit,
) {
    FilledIconButton(
        modifier = modifier,
        shape = Dimens.Shape.Circle,
        colors = colors,
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Arrow),
            tint = colors.contentColor,
            modifier = Modifier.size(Dimens.Icon),
            contentDescription = stringResource(Res.string.go_next)
        )
    }
}

@Composable
fun PreviousNextButton(
    enabled: Boolean = true,
    onClick: () -> Unit,
    isPrevious: Boolean = true,
    color: Color,
) {
    val modifier = Modifier.size(Dimens.Icon)
    val iconModifier = if (isPrevious)
        modifier else modifier.rotate(180f)

    FilledIconButton(
        enabled = enabled,
        onClick = onClick,
        colors = ComponentColors.IconButton.PreviousNextButton(color),
        shape = Dimens.Shape.Circle,
        modifier = Modifier.size(48.dp),
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.ArrowChevron),
            modifier = iconModifier,
            contentDescription = stringResource(if (isPrevious) Res.string.previous else Res.string.next)
        )
    }
}

@Composable
fun BottomNavigationActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    image: DrawableResource,
    description: StringResource? = null,
) {
    Button(
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        shape = Dimens.Shape.Circle,
        modifier = modifier,
        colors = ComponentColors.Button.BottomNavigationActionButtonColors()
    ) {
        Icon(
            painter = painterResource(image),
            contentDescription = description?.let { stringResource(description) },
            modifier = Modifier.size(Dimens.Icon),
        )
    }
}
