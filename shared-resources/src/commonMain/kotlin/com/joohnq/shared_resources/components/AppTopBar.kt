package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.button.AppDarkBackButton
import com.joohnq.shared_resources.components.button.AppLightBackButton
import com.joohnq.shared_resources.go_back
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.ui.entity.IconResource
import dev.chrisbanes.haze.ExperimentalHazeApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalHazeApi::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    isDark: Boolean = true,
    text: StringResource? = null,
    onGoBack: () -> Unit = {},
    content: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isDark) {
                AppDarkBackButton(onClick = onGoBack)
            } else {
                AppLightBackButton(onClick = onGoBack)
            }
            text?.let {
                Text(
                    text = stringResource(it),
                    style = TextStyles.textXlExtraBold(),
                    color = if (isDark) Colors.Brown80 else Colors.White,
                    textAlign = TextAlign.Center
                )
            }
        }

        content?.invoke()
    }
}

@OptIn(ExperimentalHazeApi::class)
@Composable
fun BasicAppTopBar(
    modifier: Modifier = Modifier,
    text: StringResource? = null,
    onGoBack: () -> Unit = {},
) {
    Row(
        modifier =
            modifier
                .statusBarsPadding()
                .height(56.dp)
                .paddingHorizontalMedium(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = modifier.size(48.dp),
                shape = Dimens.Shape.Circle,
                colors =
                    IconButtonColors(
                        containerColor = Colors.Transparent,
                        contentColor = Colors.Gray80,
                        disabledContainerColor = Colors.Transparent,
                        disabledContentColor = Colors.Gray80
                    ),
                onClick = onGoBack
            ) {
                IconResource(
                    IconResource(
                        icon = Drawables.Icons.Outlined.ArrowOpen,
                        tint = Colors.Gray80,
                        modifier = Modifier.size(Dimens.Icon),
                        contentDescription = Res.string.go_back
                    )
                )
            }
            text?.let {
                Text(
                    text = stringResource(it),
                    style = TextStyles.textXlExtraBold(),
                    color = Colors.Gray80,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
