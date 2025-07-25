package com.joohnq.shared_resources.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BottomNavigationButton(
    modifier: Modifier = Modifier,
    image: DrawableResource,
    description: StringResource? = null,
    onClick: () -> Unit = {},
) {
    Button(
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        shape = Dimens.Shape.Circle,
        modifier = modifier,
        colors = ComponentColors.Button.bottomNavigationActionButtonColors()
    ) {
        Icon(
            painter = painterResource(image),
            contentDescription = description?.let { stringResource(description) },
            modifier = Modifier.size(Dimens.Icon),
        )
    }
}