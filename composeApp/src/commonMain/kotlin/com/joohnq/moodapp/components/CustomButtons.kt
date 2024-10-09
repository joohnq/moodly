package com.joohnq.moodapp.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.entities.IconProps
import org.jetbrains.compose.resources.painterResource

@Composable
fun ButtonTextWithIcon(
    text: String,
    icon: IconProps,
    colors: ButtonColors,
    shape: Shape,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.height(56.dp),
        colors = colors,
        shape = shape,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text, style = CustomTextStyle.TextStyleWelcomeScreenButton())
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
    borderStroke: BorderStroke,
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
fun ButtonWithArrowRight(text: String, onClick: () -> Unit) {
    ButtonTextWithIcon(
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
        borderStroke = BorderStroke(1.5.dp, color = Colors.Brown80),
        onClick = onClick
    )
}