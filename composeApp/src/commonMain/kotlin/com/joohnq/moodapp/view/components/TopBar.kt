package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopBarDark(
    modifier: Modifier = Modifier,
    text: StringResource,
    onGoBack: () -> Unit,
    content: (@Composable () -> Unit)? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ButtonWithArrowOpen(color = Colors.Brown80, onClick = onGoBack)
            HorizontalSpacer(10.dp)
            Text(
                text = stringResource(text),
                style = TextStyles.TextXlExtraBold(),
                color = Colors.Brown80,
            )
        }
        content?.let { it() }
    }
}

@Composable
fun HomeTopBar(modifier: Modifier = Modifier, userName: String, date: String) {
    Column(
        modifier = Modifier.fillMaxSize().background(
            color = Colors.White, shape = RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
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
fun TopBarLight(
    modifier: Modifier = Modifier,
    text: StringResource,
    onGoBack: () -> Unit,
    content: (@Composable () -> Unit)? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ButtonWithArrowOpen(color = Colors.White, onClick = onGoBack)
            HorizontalSpacer(10.dp)
            Text(
                text = stringResource(text),
                style = TextStyles.TextXlExtraBold().copy(color = Color.White)
            )
        }
        content?.let { it() }
    }
}

@Composable
fun TopBarLight(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    content: (@Composable () -> Unit)? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ButtonWithArrowOpen(color = Colors.White, onClick = onGoBack)
        }
        content?.let { it() }
    }
}

@Composable
fun TopBarDark(
    modifier: Modifier = Modifier,
    onGoBack: () -> Unit,
    content: (@Composable () -> Unit)? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(vertical = 12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            ButtonWithArrowOpen(color = Colors.Brown80, onClick = onGoBack)
        }
        content?.let { it() }
    }
}
