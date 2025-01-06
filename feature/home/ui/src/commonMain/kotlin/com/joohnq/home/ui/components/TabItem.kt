package com.joohnq.home.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.DIcon
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TabItem(
    icon: DIcon,
    selected: Boolean,
    onNavigate: () -> Unit,
) {
    FilledIconButton(
        shape = Dimens.Shape.Circle,
        onClick = onNavigate,
        modifier = Modifier.size(56.dp),
        colors = IconButtonColors(
            containerColor = if (selected) Colors.Brown10 else Colors.White,
            contentColor = if (selected) Colors.Brown80 else Colors.Brown30,
            disabledContainerColor = Color.Unspecified,
            disabledContentColor = Color.Unspecified
        )
    ) {
        Icon(
            painter = painterResource(icon.icon),
            contentDescription = stringResource(icon.contentDescription),
            modifier = icon.modifier
        )
    }
//    NavigationBarItem(
//        modifier = Modifier.size(24.dp).height(50.dp).background(color = Colors.Brown30),
//        selected = selected,
//        colors = NavigationBarItemDefaults.colors(
//            selectedIconColor = Colors.Brown80,
//            unselectedIconColor = Colors.Brown30,
//            indicatorColor = Colors.Transparent,
//        ),
//        onClick = onNavigate,
//        icon = {
//
//        },
//    )
}