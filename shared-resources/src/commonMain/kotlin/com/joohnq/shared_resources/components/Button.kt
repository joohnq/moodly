package com.joohnq.shared_resources.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.joohnq.domain.Platform
import com.joohnq.domain.Platform.*
import com.joohnq.domain.entity.DIcon
import com.joohnq.domain.platform
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.continue_word
import com.joohnq.shared_resources.go_back
import com.joohnq.shared_resources.go_next
import com.joohnq.shared_resources.sign_in
import com.joohnq.shared_resources.sign_in_with_apple
import com.joohnq.shared_resources.sign_in_with_email
import com.joohnq.shared_resources.sign_in_with_google
import com.joohnq.shared_resources.sign_up
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProgressButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors,
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    progressColor: Color = Color.White,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = colors,
        enabled = !isLoading,
    ) {
        AnimatedContent(
            targetState = isLoading,
            transitionSpec = {
                fadeIn(tween(300)) togetherWith fadeOut(tween(300))
            },
            label = "ProgressButtonContent"
        ) { loading ->
            if (loading) {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxSize()
                        .sizeIn(maxWidth = 40.dp, maxHeight = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = progressColor,
                        strokeWidth = 3.dp,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            } else {
                content()
            }
        }
    }
}


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
                style = TextStyles.textLgExtraBold(),
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
                style = TextStyles.textLgExtraBold(),
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
            icon = Drawables.Icons.Outlined.Arrow,
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
            icon = Drawables.Icons.Outlined.Check,
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
                icon = Drawables.Icons.Outlined.ArrowOpen,
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
            painter = painterResource(Drawables.Icons.Outlined.Arrow),
            tint = colors.contentColor,
            modifier = Modifier.size(Dimens.Icon),
            contentDescription = stringResource(Res.string.go_next)
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


@Composable
fun IconTextButton(
    modifier: Modifier = Modifier,
    icon: DIcon,
    colors: ButtonColors,
    shape: Shape = CircleShape,
    text: StringResource,
    onClick: () -> Unit = {},
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
            Icon(icon)
            HorizontalSpacer(12.dp)
            Text(
                text = stringResource(text),
                style = TextStyles.textMdSemiBold(),
            )
        }
    }
}

@Composable
fun SignInButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val colors = ButtonDefaults.buttonColors(
        containerColor = Colors.Brown60,
        contentColor = Colors.White,
        disabledContainerColor = Colors.Brown60,
        disabledContentColor = Colors.White
    )
    ProgressButton(
        modifier = modifier.fillMaxWidth().height(48.dp),
        isLoading = isLoading,
        colors = colors,
        isEnabled = isEnabled,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.sign_in),
                style = TextStyles.textLgExtraBold(),
            )
            HorizontalSpacer(12.dp)
            Icon(
                DIcon(
                    icon = Drawables.Icons.Outlined.Arrow,
                    tint = Colors.White,
                    modifier = Modifier.size(20.dp),
                    contentDescription = Res.string.sign_in
                )
            )
        }
    }
}

@Composable
fun SignUpButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val colors = ButtonDefaults.buttonColors(
        containerColor = Colors.Brown60,
        contentColor = Colors.White,
        disabledContainerColor = Colors.Brown60,
        disabledContentColor = Colors.White
    )

    ProgressButton(
        modifier = modifier.fillMaxWidth().height(48.dp),
        isLoading = isLoading,
        colors = colors,
        isEnabled = isEnabled,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.sign_up),
                style = TextStyles.textLgExtraBold(),
            )
            HorizontalSpacer(12.dp)
            Icon(
                DIcon(
                    icon = Drawables.Icons.Outlined.Arrow,
                    tint = if (isEnabled) colors.contentColor else colors.disabledContentColor,
                    modifier = Modifier.size(20.dp),
                    contentDescription = Res.string.sign_up
                )
            )
        }
    }
}

@Composable
@Preview
fun ButtonPreview() {
    Column {
        val modifier = Modifier.fillMaxWidth().height(48.dp)
        ProgressButton(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Colors.Gray100,
                contentColor = Colors.White,
                disabledContainerColor = Colors.Gray100,
                disabledContentColor = Colors.White
            ),
            isLoading = false,
            content = { Text("Sign In") },
            onClick = {}
        )
        ProgressButton(
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = Colors.Gray100,
                contentColor = Colors.White,
                disabledContainerColor = Colors.Gray100,
                disabledContentColor = Colors.White
            ),
            isLoading = true,
            content = { Text("Sign In") },
            onClick = {}
        )
    }
}
