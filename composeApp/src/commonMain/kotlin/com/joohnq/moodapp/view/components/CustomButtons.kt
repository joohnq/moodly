package com.joohnq.moodapp.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.view.entities.IconProps
import com.joohnq.moodapp.view.onboarding.options.StressRateOptions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ButtonTextWithIcon(
    modifier: Modifier = Modifier,
    text: String,
    icon: IconProps,
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
                text,
                style = TextStyles.WelcomeScreenButton()
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(icon.icon),
                contentDescription = icon.contentDescription,
                tint = icon.tint,
                modifier = icon.modifier
            )
        }
    }
}

@Composable
fun ButtonWithIcon(
    modifier: Modifier,
    icon: IconProps,
    colors: ButtonColors,
    borderStroke: BorderStroke? = null,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = colors,
        shape = CircleShape,
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        border = borderStroke
    ) {
        Icon(
            painter = painterResource(icon.icon),
            contentDescription = icon.contentDescription,
            tint = icon.tint,
            modifier = icon.modifier
        )
    }
}

@Composable
fun ButtonWithArrowRight(
    modifier: Modifier = Modifier,
    text: String,
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
        icon = IconProps(
            icon = Drawables.Icons.Arrow,
            tint = Colors.White,
            modifier = Modifier.size(24.dp)
        ),
        onClick = onClick
    )
}

@Composable
fun ButtonWithArrowOpen(onClick: () -> Unit) {
    ButtonWithIcon(
        modifier = Modifier.size(48.dp),
        colors = ButtonColors(
            containerColor = Colors.Transparent,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.Transparent,
            disabledContentColor = Colors.Brown80
        ),
        icon = IconProps(
            icon = Drawables.Icons.ArrowOpen,
            tint = Colors.Brown80,
            modifier = Modifier.size(24.dp),
        ),
        borderStroke = BorderStroke(
            1.5.dp,
            color = Colors.Brown80
        ),
        onClick = onClick
    )
}

@Composable
fun ButtonWithText(
    modifier: Modifier = Modifier,
    text: String,
    colors: ButtonColors,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(56.dp),
        shape = CircleShape,
        colors = colors
    ) {
        Text(
            text,
            style = TextStyles.WelcomeScreenButton(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ButtonWithArrowRight(
    modifier: Modifier = Modifier,
    colors: ButtonColors,
    onClick: () -> Unit
) {
    ButtonWithIcon(
        modifier = modifier,
        colors = colors,
        borderStroke = null,
        icon = IconProps(
            icon = Drawables.Icons.Arrow,
            tint = colors.contentColor,
            modifier = Modifier.size(24.dp)
        ),
        onClick = onClick
    )
}

@Composable
fun StressRateButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    option: StressRateOptions,
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
        Text(text = stringResource(option.value))
    }
}