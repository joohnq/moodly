package com.joohnq.moodapp.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.Icon
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.TextStyles
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.continue_word
import moodapp.composeapp.generated.resources.go_back
import moodapp.composeapp.generated.resources.go_next
import moodapp.composeapp.generated.resources.next
import moodapp.composeapp.generated.resources.previous
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ButtonTextAndIcon(
    modifier: Modifier = Modifier,
    text: StringResource,
    icon: Icon,
    colors: ButtonColors,
    shape: Shape,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.height(56.dp),
        colors = colors,
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
                color = Colors.White
            )
            HorizontalSpacer(12.dp)
            Icon(icon)
        }
    }
}

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    text: StringResource = Res.string.continue_word,
    onClick: () -> Unit
) {
    ButtonTextAndIcon(
        modifier = modifier,
        text = text,
        colors = ComponentColors.Button.MainButtonColors(),
        shape = Dimens.Shape.Circle,
        icon = Icon(
            icon = Drawables.Icons.Arrow,
            tint = Colors.White,
            modifier = Modifier.size(Dimens.Icon),
            contentDescription = Res.string.continue_word
        ),
        onClick = onClick
    )
}

@Composable
fun ButtonTextAndCheck(
    modifier: Modifier = Modifier,
    text: StringResource,
    onClick: () -> Unit
) {
    ButtonTextAndIcon(
        modifier = modifier,
        text = text,
        colors = ComponentColors.Button.MainButtonColors(),
        shape = Dimens.Shape.Circle,
        icon = Icon(
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
    onClick: () -> Unit
) {
    OutlinedIconButton(
        modifier = Modifier.size(48.dp),
        shape = Dimens.Shape.Circle,
        colors = IconButtonColors(
            containerColor = backgroundColor,
            contentColor = color,
            disabledContainerColor = backgroundColor,
            disabledContentColor = color
        ),
        border = BorderStroke(
            1.5.dp,
            color = color
        ),
        onClick = onClick,
    ) {
        Icon(
            Icon(
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
    onClick: () -> Unit
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
