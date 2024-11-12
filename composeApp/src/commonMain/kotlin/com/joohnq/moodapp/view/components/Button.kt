package com.joohnq.moodapp.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.Icon
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.continue_word
import moodapp.composeapp.generated.resources.go_back
import moodapp.composeapp.generated.resources.go_next
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ButtonTextWithIcon(
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
            VerticalSpacer(12.dp)
            Icon(icon)
        }
    }
}

@Composable
fun ButtonWithIcon(
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    icon: Icon,
    borderStroke: BorderStroke? = null,
    colors: IconButtonColors,
    onClick: () -> Unit
) {
    OutlinedIconButton(
        enabled = enabled,
        modifier = modifier,
        shape = CircleShape,
        colors = colors,
        border = borderStroke,
        onClick = onClick,
    ) {
        Icon(icon)
    }
}

@Composable
fun ButtonWithArrowRight(
    modifier: Modifier = Modifier,
    text: StringResource,
    onClick: () -> Unit
) {
    ButtonTextWithIcon(
        modifier = modifier,
        text = text,
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.Brown80,
            contentColor = Colors.White
        ),
        shape = CircleShape,
        icon = Icon(
            icon = Drawables.Icons.Arrow,
            tint = Colors.White,
            modifier = Modifier.size(24.dp),
            contentDescription = Res.string.continue_word
        ),
        onClick = onClick
    )
}

@Composable
fun ButtonWithCheck(
    modifier: Modifier = Modifier,
    text: StringResource,
    onClick: () -> Unit
) {
    ButtonTextWithIcon(
        modifier = modifier,
        text = text,
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80
        ),
        shape = CircleShape,
        icon = Icon(
            icon = Drawables.Icons.Check,
            tint = Colors.Brown80,
            modifier = Modifier.size(24.dp),
            contentDescription = text
        ),
        onClick = onClick
    )
}

@Composable
fun ButtonWithArrowOpen(
    color: Color,
    backgroundColor: Color = Colors.Transparent,
    onClick: () -> Unit
) {
    ButtonWithIcon(
        modifier = Modifier.size(48.dp),
        colors = IconButtonColors(
            containerColor = backgroundColor,
            contentColor = color,
            disabledContainerColor = backgroundColor,
            disabledContentColor = color
        ),
        icon = Icon(
            icon = Drawables.Icons.ArrowOpen,
            tint = color,
            modifier = Modifier.size(24.dp),
            contentDescription = Res.string.go_back
        ),
        borderStroke = BorderStroke(
            1.5.dp,
            color = color
        ),
        onClick = onClick
    )
}

@Composable
fun ButtonWithArrowRight(
    modifier: Modifier = Modifier,
    colors: IconButtonColors,
    onClick: () -> Unit
) {
    ButtonWithIcon(
        modifier = modifier,
        colors = colors,
        borderStroke = null,
        icon = Icon(
            icon = Drawables.Icons.Arrow,
            tint = colors.contentColor,
            modifier = Modifier.size(24.dp),
            contentDescription = Res.string.go_next
        ),
        onClick = onClick
    )
}

@Composable
fun StressRateButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    option: StressLevel,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.aspectRatio(1f),
        onClick = onClick,
        colors = ButtonColors(
            containerColor = if (isSelected) Colors.Orange40 else Colors.White,
            contentColor = if (isSelected) Colors.White else Colors.Brown80,
            disabledContainerColor = if (isSelected) Colors.Orange40 else Colors.White,
            disabledContentColor = if (isSelected) Colors.White else Colors.Brown80
        ),
        border = if (isSelected) BorderStroke(
            color = Colors.Orange40Alpha25,
            width = 4.dp
        ) else null,
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
    ) {
        Text(
            text = stringResource(option.value),
            style = TextStyles.HeadingXsExtraBold(),
            color = Colors.Brown80
        )
    }
}