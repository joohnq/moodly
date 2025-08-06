package com.joohnq.shared_resources.components.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.delete
import com.joohnq.shared_resources.more
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CardWithMoreMenuLayout(
    modifier: Modifier = Modifier,
    menuContainerColor: Color = Colors.Brown80,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = onClick != null,
    onDelete: () -> Unit = {},
    secondary: @Composable (Modifier) -> Unit = {},
    content: @Composable (Modifier) -> Unit,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .paddingHorizontalMedium(),
        onClick = { onClick?.invoke() },
        enabled = enabled,
        colors =
            CardColors(
                containerColor = Colors.Gray5,
                contentColor = Color.Unspecified,
                disabledContainerColor = Colors.Gray5,
                disabledContentColor = Color.Unspecified
            ),
        shape = Dimens.Shape.Large
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            content(Modifier.weight(1f))

            Box {
                IconButton(
                    onClick = { isMenuExpanded = !isMenuExpanded }
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Filled.MoreVertical),
                        contentDescription = stringResource(Res.string.more),
                        tint = Colors.Brown80,
                        modifier = Modifier.size(Dimens.Icon)
                    )
                }
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false },
                    containerColor = menuContainerColor,
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp,
                    shape = Dimens.Shape.ExtraSmall
                ) {
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        colors =
                            MenuItemColors(
                                textColor = Colors.Brown80,
                                leadingIconColor = Color.Unspecified,
                                trailingIconColor = Color.Unspecified,
                                disabledTextColor = Color.Unspecified,
                                disabledLeadingIconColor = Color.Unspecified,
                                disabledTrailingIconColor = Color.Unspecified
                            ),
                        text = {
                            Text(
                                text = stringResource(Res.string.delete),
                                style = TextStyles.textMdSemiBold(),
                                color = Colors.White
                            )
                        },
                        onClick = onDelete
                    )
                }
            }
        }
        secondary(Modifier.fillMaxWidth())
    }
}
