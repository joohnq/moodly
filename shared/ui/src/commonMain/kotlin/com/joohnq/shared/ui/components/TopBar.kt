package com.joohnq.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingVerticalMedium
import com.joohnq.shared.ui.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeTopBar(modifier: Modifier = Modifier, userName: String, date: String) {
    Column(
        modifier = Modifier.fillMaxSize().background(
            color = Colors.White, shape = Dimens.Shape.BottomMedium
        ).padding(20.dp).then(modifier)
    ) {
        Row {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Calendar),
                    modifier = Modifier.size(20.dp),
                    tint = Colors.Brown80,
                    contentDescription = null
                )
                Text(text = date, style = TextStyles.TextSmBold(), color = Colors.Brown100Alpha64)
            }
        }
        VerticalSpacer(10.dp)
        Text(
            text = "Hi, $userName", style = TextStyles.HeadingSmExtraBold(), color = Colors.Brown80
        )
    }
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    isDark: Boolean = true,
    text: StringResource? = null,
    onGoBack: () -> Unit,
    content: (@Composable () -> Unit)? = null,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().paddingVerticalMedium()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            BackButton(
                color = if (isDark) Colors.Brown80 else Colors.White,
                onClick = onGoBack
            )
            text?.let {
                HorizontalSpacer(10.dp)

                Text(
                    text = stringResource(text),
                    style = TextStyles.TextXlExtraBold(),
                    color = if (isDark) Colors.Brown80 else Colors.White,
                )
            }
        }
        content?.let { it() }
    }
}
